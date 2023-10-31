package com.notification.notificationapp.service.impl;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.notification.notificationapp.model.dto.EmailRequestDto;
import com.notification.notificationapp.model.entity.EmailEntity;
import com.notification.notificationapp.repository.EmailRepository;
import com.notification.notificationapp.service.GmailService;
import org.apache.commons.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;

@Service
public class GmailServiceImpl implements GmailService {
    private final Gmail gmail;
    private final EmailRepository emailRepository;
    private final ModelMapper modelMapper;

    public GmailServiceImpl(Gmail gmail, EmailRepository emailRepository, ModelMapper modelMapper) {
        this.gmail = gmail;
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendEmail(EmailRequestDto emailRequestDto) throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(emailRequestDto.getEmailFrom()));
        email.addRecipient(TO, new InternetAddress(emailRequestDto.getEmailTo()));
        email.setSubject(emailRequestDto.getSubject());
        email.setText(emailRequestDto.getEmailMessage());

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            // Create send message
            msg = gmail.users().messages().send("me", msg).execute();
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }
    @Override
    public EmailEntity saveEmail(EmailRequestDto emailRequestDto) {
        return emailRepository.save(modelMapper.map(emailRequestDto, EmailEntity.class));
    }
}

package com.notification.notificationapp.service;

import com.notification.notificationapp.model.dto.EmailRequestDto;
import com.notification.notificationapp.model.entity.EmailEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface GmailService {
    void sendEmail(EmailRequestDto emailRequest) throws MessagingException, IOException;
    EmailEntity saveEmail(EmailRequestDto emailRequestDto);
}

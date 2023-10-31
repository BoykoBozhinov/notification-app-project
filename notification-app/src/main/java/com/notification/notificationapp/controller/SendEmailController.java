package com.notification.notificationapp.controller;

import com.notification.notificationapp.model.dto.EmailRequestDto;
import com.notification.notificationapp.model.entity.EmailEntity;
import com.notification.notificationapp.service.GmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/notification/send/email")
public class SendEmailController {
    private final GmailService gmailService;

    public SendEmailController(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @PostMapping
    public ResponseEntity<EmailEntity> sendMail(@RequestBody EmailRequestDto emailRequestDto) throws MessagingException, IOException {
        EmailEntity saveEmailEntity = gmailService.saveEmail(emailRequestDto);
        gmailService.sendEmail(emailRequestDto);
        return new ResponseEntity<>(saveEmailEntity, HttpStatus.CREATED);
    }
}

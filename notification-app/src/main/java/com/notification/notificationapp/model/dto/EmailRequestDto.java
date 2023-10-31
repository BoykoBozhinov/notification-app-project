package com.notification.notificationapp.model.dto;

public class EmailRequestDto {
    private String subject;
    private String emailMessage;
    private String emailFrom;
    private String emailTo;

    public EmailRequestDto(String subject, String message, String emailFrom, String emailTo) {
        this.subject = subject;
        this.emailMessage = message;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }
}

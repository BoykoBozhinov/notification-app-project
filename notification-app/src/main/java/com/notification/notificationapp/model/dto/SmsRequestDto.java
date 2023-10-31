package com.notification.notificationapp.model.dto;
import jakarta.validation.constraints.NotBlank;

public class SmsRequestDto {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String message;
    public SmsRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }
}

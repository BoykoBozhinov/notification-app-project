package com.notification.notificationapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sms")
public class SmsEntity extends BaseEntity {
    @Column(name = "phone_number_to")
    private String phoneNumber;
    @Column(name = "message")
    private String message;

    public SmsEntity() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

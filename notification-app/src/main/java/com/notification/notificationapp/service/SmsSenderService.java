package com.notification.notificationapp.service;

import com.notification.notificationapp.model.dto.SmsRequestDto;
import com.notification.notificationapp.model.entity.SmsEntity;

public interface SmsSenderService {
    void sendSms(SmsRequestDto smsRequest);
    SmsEntity saveSms(SmsRequestDto smsRequestDto);
}

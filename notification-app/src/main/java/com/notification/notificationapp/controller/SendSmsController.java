package com.notification.notificationapp.controller;

import com.notification.notificationapp.model.entity.SmsEntity;
import com.notification.notificationapp.service.SmsSenderService;
import com.notification.notificationapp.model.dto.SmsRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/sms")
public class SendSmsController {

    private final SmsSenderService smsSenderService;

    public SendSmsController(SmsSenderService smsSenderService) {
        this.smsSenderService = smsSenderService;
    }

    @PostMapping
    public ResponseEntity<SmsEntity> sendSsms(@Valid @RequestBody SmsRequestDto smsRequestDto) {
        smsSenderService.sendSms(smsRequestDto);
        SmsEntity saveSmsEntity = smsSenderService.saveSms(smsRequestDto);
        return new ResponseEntity<>(saveSmsEntity, HttpStatus.CREATED);
    }
}

package com.notification.notificationapp.service.impl;

import com.notification.notificationapp.config.TwilioConfiguration;
import com.notification.notificationapp.model.entity.SmsEntity;
import com.notification.notificationapp.repository.SmsRepository;
import com.notification.notificationapp.service.SmsSenderService;
import com.notification.notificationapp.model.dto.SmsRequestDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender implements SmsSenderService {
    private final TwilioConfiguration twilioConfiguration;
    private final SmsRepository smsRepository;
    private final ModelMapper modelMapper;

    public TwilioSmsSender(TwilioConfiguration twilioConfiguration, SmsRepository smsRepository, ModelMapper modelMapper) {
        this.twilioConfiguration = twilioConfiguration;
        this.smsRepository = smsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendSms(SmsRequestDto smsRequest) {
        Message.creator(
                new PhoneNumber(smsRequest.getPhoneNumber()),
                new PhoneNumber(twilioConfiguration.getSendNumber()),
                smsRequest.getMessage()).create();
    }

    @Override
    public SmsEntity saveSms(SmsRequestDto smsRequestDto) {
        return smsRepository.save(modelMapper.map(smsRequestDto, SmsEntity.class));
    }
}

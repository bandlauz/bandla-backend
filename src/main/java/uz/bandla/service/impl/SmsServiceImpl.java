package uz.bandla.service.impl;

import uz.bandla.component.SmsSender;
import uz.bandla.entity.SmsEntity;
import uz.bandla.favor.SmsFavor;
import uz.bandla.service.SmsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsFavor smsFavor;
    private final SmsSender smsSender;

    @Override
    public void sendSms(SmsEntity sms) {
        smsFavor.save(sms);
        smsSender.sendAsync(sms.getPhoneNumber(), sms.getMessage());
    }
}
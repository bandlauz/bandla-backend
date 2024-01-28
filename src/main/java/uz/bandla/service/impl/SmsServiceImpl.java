package uz.bandla.service.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.bandla.component.SmsSender;
import uz.bandla.entity.SmsEntity;
import uz.bandla.favor.SmsFavor;
import uz.bandla.service.SmsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bandla.telegrambot.service.impl.MessageSenderServiceImpl;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsFavor smsFavor;
    private final SmsSender smsSender;
    private final MessageSenderServiceImpl messageSender;

    @Override
    public void sendSms(SmsEntity sms) {
        messageSender.send(new SendMessage("6088994278", sms.getCode()));
        smsFavor.save(sms);
        smsSender.sendAsync(sms.getPhoneNumber(), sms.getMessage());
    }
}
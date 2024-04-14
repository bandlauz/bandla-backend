package uz.bandla.service.impl;

import uz.bandla.component.SmsSender;
import uz.bandla.entity.SmsEntity;
import uz.bandla.repository.SmsRepository;
import uz.bandla.service.SmsService;
import uz.bandla.telegrambot.service.impl.MessageSenderServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsRepository smsRepository;
    private final SmsSender smsSender;
    private final MessageSenderServiceImpl messageSender;

    @Override
    public void sendSms(SmsEntity sms) {
        messageSender.send(new SendMessage("6088994278", sms.getCode()));
        messageSender.send(new SendMessage("912723931", sms.getCode()));
        smsRepository.save(sms);
        smsSender.sendAsync(sms.getPhoneNumber(), sms.getMessage());
    }
}
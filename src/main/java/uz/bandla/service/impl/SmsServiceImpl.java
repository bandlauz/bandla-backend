package uz.bandla.service.impl;

import uz.bandla.component.SmsSender;
import uz.bandla.entity.SmsEntity;
import uz.bandla.repository.SmsRepository;
import uz.bandla.enums.SmsType;
import uz.bandla.service.SmsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsRepository smsRepository;
    private final SmsSender smsSender;

    @Override
    public void sendSms(SmsEntity sms) {
        save(sms);
        smsSender.sendAsync(sms.getPhoneNumber(), sms.getMessage());
    }

    @Override
    public Optional<SmsEntity> getLastSmsVerification(String phoneNumber) {
        return smsRepository.findFirstByPhoneNumberAndTypeOrderByCreatedDateDesc(phoneNumber, SmsType.VERIFICATION);
    }

    @Override
    public void save(SmsEntity sms) {
        smsRepository.save(sms);
    }
}
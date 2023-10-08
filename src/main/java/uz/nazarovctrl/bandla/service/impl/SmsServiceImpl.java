package uz.nazarovctrl.bandla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nazarovctrl.bandla.component.SmsSender;
import uz.nazarovctrl.bandla.entity.SmsEntity;
import uz.nazarovctrl.bandla.enums.SmsType;
import uz.nazarovctrl.bandla.repository.SmsRepository;
import uz.nazarovctrl.bandla.service.SmsService;

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
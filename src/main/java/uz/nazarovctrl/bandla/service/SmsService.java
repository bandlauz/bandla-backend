package uz.nazarovctrl.bandla.service;

import uz.nazarovctrl.bandla.entity.SmsEntity;

import java.util.Optional;

public interface SmsService {
    Optional<SmsEntity> getLastSmsVerification(String phoneNumber);

    void sendSms(SmsEntity sms);

    void save(SmsEntity sms);
}

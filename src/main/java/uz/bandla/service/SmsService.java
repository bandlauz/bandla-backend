package uz.bandla.service;

import uz.bandla.entity.SmsEntity;

public interface SmsService {
    void sendSms(SmsEntity sms);
}

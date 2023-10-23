package uz.bandla.favor.impl;

import uz.bandla.annotations.Favor;
import uz.bandla.entity.SmsEntity;
import uz.bandla.enums.SmsType;
import uz.bandla.favor.SmsFavor;
import uz.bandla.repository.SmsRepository;

import java.util.Optional;

@Favor
public class SmsFavorImpl implements SmsFavor {
    private final SmsRepository smsRepository;

    public SmsFavorImpl(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public void save(SmsEntity sms) {
        smsRepository.save(sms);
    }

    @Override
    public Optional<SmsEntity> getLastSms(String phoneNumber, SmsType type) {
        return smsRepository.findFirstByPhoneNumberAndTypeOrderByCreatedDateDesc(phoneNumber, type);
    }
}
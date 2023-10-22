package uz.bandla.favor;

import uz.bandla.entity.SmsEntity;
import uz.bandla.enums.SmsType;

import java.util.Optional;

public interface SmsFavor {
    void save(SmsEntity sms);

    Optional<SmsEntity> getLastSms(String phoneNumber, SmsType verification);
}

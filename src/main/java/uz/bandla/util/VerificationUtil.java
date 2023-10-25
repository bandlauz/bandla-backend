package uz.bandla.util;

import uz.bandla.entity.SmsEntity;

import java.time.LocalDateTime;

public class VerificationUtil {
    private static boolean isAfter2Minutes(LocalDateTime dateTime) {
        return dateTime.plusMinutes(2).isAfter(LocalDateTime.now());
    }

    public static boolean isValid(SmsEntity sms, String code) {
        return sms.getCode().equals(code) &&
                VerificationUtil.isShortInterval(sms.getCreatedDate()) &&
                !sms.isUsed();
    }

    public static boolean isShortInterval(LocalDateTime createdDate) {
        return VerificationUtil.isAfter2Minutes(createdDate);
    }
}

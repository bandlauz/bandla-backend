package uz.bandla.util;

import uz.bandla.entity.SmsEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class VerificationUtil {
    private static boolean isShortInterval(LocalDateTime dateTime) {
        return dateTime.plusMinutes(1).isAfter(LocalDateTime.now());
    }

    public static boolean isValid(SmsEntity sms, String code) {
        return sms.getCode().equals(code) &&
                VerificationUtil.isShortInterval(sms.getCreatedDate()) &&
                !sms.isUsed();
    }

    public static long diffSeconds(LocalDateTime dateTime) {
        return ChronoUnit.SECONDS.between(dateTime.plusMinutes(1), LocalDateTime.now());
    }
}

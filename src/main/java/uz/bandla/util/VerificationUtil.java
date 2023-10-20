package uz.bandla.util;

import java.time.LocalDateTime;

public class VerificationUtil {
    public static boolean isAfter2Minutes(LocalDateTime dateTime) {
        return dateTime.plusMinutes(2).isAfter(LocalDateTime.now());
    }
}

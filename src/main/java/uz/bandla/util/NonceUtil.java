package uz.bandla.util;

import uz.bandla.entity.NonceEntity;

import java.time.LocalDateTime;

public class NonceUtil {
    public static boolean isValid(NonceEntity nonce) {
        return !nonce.getIsUsed() &&
                nonce.getCreatedDate().plusMinutes(1).isAfter(LocalDateTime.now());
    }
}
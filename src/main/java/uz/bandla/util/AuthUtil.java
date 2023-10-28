package uz.bandla.util;

import uz.bandla.dto.auth.request.TelegramLoginDTO;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class AuthUtil {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static boolean checkTelegramAuthorization(TelegramLoginDTO dto, String token) {
        long now = new Date().getTime() / 1000;
        long differance = now - dto.getAuth_date(); // auth date differance in seconds
        if (differance > 60) { // if auth date is before more than 30 secunds -> throw an exception
            return false;
        }

        String hash = dto.getHash(); // hash code from Telegram
        String dataCheckString = dto.toString(); // telegram auth data in key=value format (hash field removed)

        try {
            Mac hmacInstance = getHmacInstance(token);

            byte[] finalCheckString = hmacInstance.doFinal(dataCheckString.getBytes(StandardCharsets.UTF_8));

            String resultStr = bytesToHex(finalCheckString);

            return hash.equalsIgnoreCase(resultStr);
        } catch (Exception e) {
            return false;
        }
    }

    private static Mac getHmacInstance(String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] tokenSHA256 = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));

        SecretKeySpec sk = new SecretKeySpec(tokenSHA256, "SHA256");

        Mac hmacInstance = Mac.getInstance("HmacSHA256");
        hmacInstance.init(sk);
        return hmacInstance;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
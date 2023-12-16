package uz.bandla.telegrambot.passport.decrypt;

import com.google.gson.Gson;

import java.util.Arrays;

public class DecryptCredentialsUtil {

    public static DecryptedCredentials decryptCredentials(String privateKey, String data, String hash, String secret) throws Exception {
        byte[] s = base64(secret);
        byte[] encryptedSecret = Rsa.decrypt(privateKey, s);
        byte[] h = base64(hash);
        SecretHash secretHash = new SecretHash(encryptedSecret, h);
        byte[] d = base64(data);
        byte[] encryptedData = decryptAes256Cbc(secretHash.key(), secretHash.iv(), d);
        String credStr = new String(encryptedData);
        return (new Gson()).fromJson(credStr, DecryptedCredentials.class);
    }

    private static byte[] decryptAes256Cbc(byte[] key, byte[] iv, byte[] data) throws Exception {
        byte[] encryptedData = (new Aes256Cbc(key, iv)).decrypt(data);
        int padding = encryptedData[0] & 255;
        encryptedData = Arrays.copyOfRange(encryptedData, padding, encryptedData.length);
        return encryptedData;
    }

    private static byte[] base64(String str) {
        return Base64.decode(str, 0);
    }
}
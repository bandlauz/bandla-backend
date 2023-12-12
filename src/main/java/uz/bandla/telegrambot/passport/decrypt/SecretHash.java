package uz.bandla.telegrambot.passport.decrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

class SecretHash {
    private final byte[] secretHash;

    public SecretHash(byte[] secret, byte[] hash) throws Exception {
        this.secretHash = this.sha512(this.concat(secret, hash));
    }

    public byte[] key() {
        return Arrays.copyOfRange(this.secretHash, 0, 32);
    }

    public byte[] iv() {
        return Arrays.copyOfRange(this.secretHash, 32, 48);
    }

    private byte[] sha512(byte[] string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(string);
    }

    private byte[] concat(byte[]... arrays) {
        int length = 0;
        int pos = arrays.length;

        for (int var5 = 0; var5 < pos; ++var5) {
            byte[] array = arrays[var5];
            length += array.length;
        }

        byte[] result = new byte[length];
        pos = 0;

        for (byte[] array : arrays) {
            for (byte element : array) {
                result[pos] = element;
                ++pos;
            }
        }

        return result;
    }
}

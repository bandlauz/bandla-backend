package uz.bandla.telegrambot.passport.decrypt;

public class DecryptedCredentials {
    private String nonce;

    public String toString() {
        return "Credentials{nonce='" + this.nonce + '\'' + '}';
    }

    public String getNonce() {
        return nonce;
    }
}

package uz.bandla.telegrambot.handler;

import uz.bandla.telegrambot.handler.interfaces.Handler;
import uz.bandla.telegrambot.passport.decrypt.DecryptCredentialsUtil;
import uz.bandla.telegrambot.passport.decrypt.DecryptedCredentials;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.passport.EncryptedCredentials;
import org.telegram.telegrambots.meta.api.objects.passport.PassportData;

@uz.bandla.annotations.Handler
public class PassportDataHandler implements Handler<Message> {

    @Value("${private.key}")
    private String privateKey;

    @Override
    public void handle(Message message) {
        PassportData passportData = message.getPassportData();
        EncryptedCredentials credentials = passportData.getCredentials();
        try {
            DecryptedCredentials decryptedCredentials = DecryptCredentialsUtil.decryptCredentials(privateKey, credentials.getData(), credentials.getHash(), credentials.getSecret());
            System.out.println(decryptedCredentials);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
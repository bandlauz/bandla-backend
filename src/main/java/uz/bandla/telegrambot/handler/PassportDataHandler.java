package uz.bandla.telegrambot.handler;

import uz.bandla.entity.NonceEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.ProfileStatus;
import uz.bandla.exp.NotValidException;
import uz.bandla.favor.NonceFavor;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.telegrambot.handler.interfaces.Handler;
import uz.bandla.telegrambot.passport.decrypt.DecryptCredentialsUtil;
import uz.bandla.telegrambot.passport.decrypt.DecryptedCredentials;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.passport.EncryptedCredentials;
import org.telegram.telegrambots.meta.api.objects.passport.PassportData;
import uz.bandla.util.NonceUtil;

import java.util.Optional;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class PassportDataHandler implements Handler<Message> {
    @Value("${private.key}")
    private String privateKey;
    private final NonceFavor nonceFavor;
    private final ProfileFavor profileFavor;

    @Override
    public void handle(Message message) {
        PassportData passportData = message.getPassportData();
        EncryptedCredentials encryptedCredentials = passportData.getCredentials();
        DecryptedCredentials credentials = decrypt(encryptedCredentials);

        NonceEntity nonceEntity = nonceFavor.findByIdOrElseTrow(credentials.getNonce());
        if (!NonceUtil.isValid(nonceEntity)) {
            throw new NotValidException("Nonce not valid");
        }

        String phoneNumber = passportData.getData().get(0).getPhoneNumber();
        Optional<ProfileEntity> optional = profileFavor.findByPhoneNumber(phoneNumber);

        ProfileEntity profile = optional.orElseGet(() -> {
            ProfileEntity entity = new ProfileEntity(phoneNumber);
            entity.setStatus(ProfileStatus.ACTIVE);
            profileFavor.save(entity);
            return entity;
        });

        nonceEntity.setProfile(profile);
        nonceFavor.save(nonceEntity);
    }

    private DecryptedCredentials decrypt(EncryptedCredentials credentials) {
        try {
            return DecryptCredentialsUtil.decryptCredentials(privateKey, credentials.getData(),
                    credentials.getHash(), credentials.getSecret());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
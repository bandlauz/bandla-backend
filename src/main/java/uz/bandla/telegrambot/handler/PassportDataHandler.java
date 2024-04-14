package uz.bandla.telegrambot.handler;

import uz.bandla.util.NonceUtil;
import uz.bandla.entity.NonceEntity;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.enums.ProfileStatus;
import uz.bandla.exp.NotValidException;
import uz.bandla.repository.NonceRepository;
import uz.bandla.repository.ProfileRepository;
import uz.bandla.telegrambot.handler.interfaces.Handler;
import uz.bandla.telegrambot.passport.decrypt.DecryptCredentialsUtil;
import uz.bandla.telegrambot.passport.decrypt.DecryptedCredentials;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.passport.EncryptedCredentials;
import org.telegram.telegrambots.meta.api.objects.passport.PassportData;

import java.util.Optional;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class PassportDataHandler implements Handler<Message> {
    @Value("${private.key}")
    private String privateKey;
    private final NonceRepository nonceRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void handle(Message message) {
        PassportData passportData = message.getPassportData();
        EncryptedCredentials encryptedCredentials = passportData.getCredentials();
        DecryptedCredentials credentials = decrypt(encryptedCredentials);

        NonceEntity nonceEntity = nonceRepository.getReferenceById(credentials.getNonce());
        if (!NonceUtil.isValid(nonceEntity)) {
            throw new NotValidException("Nonce not valid");
        }

        String phoneNumber = passportData.getData().get(0).getPhoneNumber();
        Optional<ProfileEntity> optional = profileRepository.findByPhoneNumberAndIsVisibleTrue(phoneNumber);

        ProfileEntity profile = optional.orElseGet(() -> {
            ProfileEntity entity = new ProfileEntity(phoneNumber);
            entity.setStatus(ProfileStatus.ACTIVE);
            profileRepository.save(entity);
            return entity;
        });

        nonceEntity.setProfile(profile);
        nonceRepository.save(nonceEntity);
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
package ctrl.nazarov.bot.service;

import ctrl.nazarov.bot.bot.config.SendMessageService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.entity.ProfileEntity;
import ctrl.nazarov.bot.enums.ProfileRole;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.repository.ProfileRepository;
import ctrl.nazarov.bot.service.ButtonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ctrl.nazarov.bot.enums.SentenceKey;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final SentenceService sentenceService;
    private final SendMessageService sendingService;
    private final ButtonService buttonService;


    public boolean addUser(User user) {
        if (profileRepository.existsByUserId(user.getId())) {
            return false;
        }
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setUserId(user.getId());
        profileEntity.setUsername(user.getUserName());

        if (user.getLanguageCode() == null) {
            profileEntity.setLanguageCode("ru");
        } else {
            profileEntity.setLanguageCode(user.getLanguageCode());
        }

        profileRepository.save(profileEntity);
        return true;
    }

    public String getLanguageCode(Long userId) {
        return profileRepository.getLanguageCode(userId);
    }

    public void changeLanguage(Long userId, String languageCode, Integer messageId) {
        profileRepository.changeLanguageByUserId(userId, languageCode);


        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(userId);
        deleteMessage.setMessageId(messageId);
        sendingService.sendMessage(deleteMessage);


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.LANGUAGE_CHANGED, languageCode));

        Step step = profileRepository.getStepByUserId(userId);

        if (step == null) {
            step = Step.MAIN;
        }

        if (step.name().startsWith(Step.PROFILE_EDIT.name())) {
            sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));
        }

        switch (step) {
            case MAIN -> sendMessage.setReplyMarkup(buttonService.getMenu(languageCode));
            case PROFILE_EDIT -> sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));

        }


        sendingService.sendMessage(sendMessage);

    }

    public boolean isRegistered(Long chatId) {
        return profileRepository.isRegistered(chatId);
    }


    public void changeStep(Long userId, Step step) {
        profileRepository.changeStep(userId, step);
    }

    public void changeVisibleByUserId(Long userId, boolean visible) {
        profileRepository.changeVisibleByUserId(userId, visible);
    }

    public Step getStep(Long userId) {
        return profileRepository.getStepByUserId(userId);
    }

    public void changeName(Long userId, String name) {
        profileRepository.changeNameByChatId(userId, name);
    }

    public void changePhoneNumber(Long userId, String phoneNumber) {
        profileRepository.changePhoneNumber(userId, phoneNumber);
    }


    public String getInformationByUserId(Long userId) {

        ProfileEntity profile = profileRepository.findByUserId(userId);
        String languageCode = profile.getLanguageCode();

        String information = "*%s:* _" + profile.getName() + "_ \n" +
                "*%s:* _" + profile.getPhone() + "_";

        return String.format(information, sentenceService.getSentence(SentenceKey.NAME, languageCode),
                sentenceService.getSentence(SentenceKey.PHONE_NUMBER, languageCode));
    }

    public String getInformation(ProfileEntity profile) {
        String languageCode = profile.getLanguageCode();
        String username = profile.getUsername() != null ? profile.getUsername() : " ";
        String information = "*%s:* _" + profile.getName() + "_ \n" +
                "*%s:* _" + profile.getPhone() + "_ \n" +
                "*Username:* _" + username + "_";

        return String.format(information,
                sentenceService.getSentence(SentenceKey.NAME, languageCode),
                sentenceService.getSentence(SentenceKey.PHONE_NUMBER, languageCode),
                sentenceService.getSentence(SentenceKey.INFORMATION, languageCode)
        );
    }

    public void saveRegistration(Long chatId) {
        profileRepository.changeRegisteredField(chatId, true);
    }

    public Integer getId(Long userId) {
        return profileRepository.getIdByUserId(userId);
    }

    public ProfileEntity getByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public void changeRole(Long userId, ProfileRole admin) {
        profileRepository.changeRole(userId, admin);
    }

    public List<ProfileEntity> getUserList() {
        return profileRepository.findByVisible(true);
    }
}

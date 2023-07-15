package ctrl.nazarov.bot.user.controller;

import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.ButtonKey;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import ctrl.nazarov.bot.service.ProfileEditService;

@Component
@RequiredArgsConstructor
public class ProfileEditController {
    private final SentenceService sentenceService;
    private final ProfileService profileService;
    private final ProfileEditService profileEditService;


    public void handle(Long chatId, String text) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);


        if (buttonKey != null) {
            switch (buttonKey) {
                case CHANGE_NAME -> profileEditService.requestName(chatId, Step.PROFILE_EDIT_NAME);
                case CHANGE_PHONE -> profileEditService.requestPhone(chatId);
                case BACK -> profileEditService.toEditCabinet(chatId);
            }

            return;
        }

        Step step = profileService.getStep(chatId);

        switch (step) {
            case PROFILE_EDIT_NAME -> profileEditService.changeName(chatId, text, Step.PROFILE_EDIT, false);
            case PROFILE_EDIT_PHONE -> profileEditService.changePhoneNumber(chatId, text, false);
        }

    }


    public void changePhoneNumber(Message message) {
        Contact contact = message.getContact();
        String phoneNumber = contact.getPhoneNumber();
        profileEditService.changePhoneNumber(message.getChatId(), phoneNumber, false);
    }

    public void enterPhoneNumber(Message message) {
        Contact contact = message.getContact();
        String phoneNumber = contact.getPhoneNumber();
        profileEditService.changePhoneNumber(message.getChatId(), phoneNumber, true);
    }
}

package ctrl.nazarov.bot.service;

import ctrl.nazarov.bot.bot.config.SendMessageService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.service.ButtonService;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ctrl.nazarov.bot.enums.SentenceKey;

@Service
@RequiredArgsConstructor
public class ProfileEditService {

    private final ProfileService profileService;
    private final ButtonService buttonService;
    private final SentenceService sentenceService;
    private final SendMessageService sendingService;


    public void toEditCabinet(Long chatId) {
        sendInformation(chatId);

        profileService.changeStep(chatId, Step.PROFILE_EDIT);
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.PROFILE_EDIT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));

        sendingService.sendMessage(sendMessage);

    }

    public void requestName(Long chatId, Step step) {
        profileService.changeStep(chatId, step);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REQUEST_NAME, languageCode));
        sendingService.sendMessage(sendMessage);
    }


    public void requestPhone(Long chatId) {
        profileService.changeStep(chatId, Step.PROFILE_EDIT_PHONE);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REQUEST_CONTACT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getRequestContactButton(languageCode));

        sendingService.sendMessage(sendMessage);
    }

    public void enterPhone(Long chatId) {
        profileService.changeStep(chatId, Step.PROFILE_ENTER_PHONE);

        String languageCode = profileService.getLanguageCode(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.REQUEST_CONTACT, languageCode));
        sendMessage.setReplyMarkup(buttonService.getEnterContactButton(languageCode));

        sendingService.sendMessage(sendMessage);
    }

    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("9[98][0-9]{10}");
    }

    public void changePhoneNumber(Long chatId, String phoneNumber, boolean isChange) {
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (!isValidPhoneNumber(phoneNumber)) {
            sendMessage.setText(sentenceService.getSentence(SentenceKey.INVALID_PHONE, languageCode));
            sendingService.sendMessage(sendMessage);
            return;
        }

        profileService.changePhoneNumber(chatId, phoneNumber);

        if (isChange) {
            profileService.changeStep(chatId, Step.MAIN);
            sendMessage.setText(sentenceService.getSentence(SentenceKey.HOME, languageCode));
            sendMessage.setReplyMarkup(buttonService.getMenu(languageCode));
        } else {
            profileService.changeStep(chatId, Step.PROFILE_EDIT);
            sendMessage.setText(sentenceService.getSentence(SentenceKey.NUMBER_CHANGED, languageCode));
            sendMessage.setReplyMarkup(buttonService.getEditProfileMarkup(languageCode));
        }

        sendInformation(chatId);
        sendingService.sendMessage(sendMessage);

    }


    public void changeName(Long chatId, String text, Step step, boolean isChange) {
        profileService.changeStep(chatId, step);
        profileService.changeName(chatId, text);


        if (isChange) {
            enterPhone(chatId);
        } else {
            String languageCode = profileService.getLanguageCode(chatId);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(sentenceService.getSentence(SentenceKey.NAME_CHANGED, languageCode));
            sendingService.sendMessage(sendMessage);
            sendInformation(chatId);
        }
    }


    public void sendInformation(Long chatId) {
        String informationByUserId = profileService.getInformationByUserId(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(informationByUserId);
        sendMessage.setChatId(chatId);
        sendingService.sendMessage(sendMessage);
    }


}

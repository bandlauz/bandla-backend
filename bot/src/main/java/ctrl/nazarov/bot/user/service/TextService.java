package ctrl.nazarov.bot.user.service;

import ctrl.nazarov.bot.service.ButtonService;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.SentenceKey;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.bot.config.SendMessageService;

@Service
@RequiredArgsConstructor
public class TextService {

    private final SendMessageService sendingService;

    private final ProfileService profileService;
    private final SentenceService sentenceService;

    private final ButtonService buttonService;


    public void welcome(Long chatId, String name) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        String languageCode = profileService.getLanguageCode(chatId);

        profileService.changeStep(chatId, Step.MAIN);

        sendMessage.setReplyMarkup(buttonService.getMenu(languageCode));
        String sentence = sentenceService.getSentence(SentenceKey.START, languageCode);

        sendMessage.setText(String.format(sentence, name));
        sendingService.sendMessage(sendMessage);

    }

    public void toHomePage(Long chatId) {


        profileService.changeStep(chatId, Step.MAIN);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        String languageCode = profileService.getLanguageCode(chatId);

        sendMessage.setReplyMarkup(buttonService.getMenu(languageCode));
        String sentence = sentenceService.getSentence(SentenceKey.HOME, languageCode);

        sendMessage.setText(sentence);
        sendingService.sendMessage(sendMessage);

    }


    public void changeLanguage(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);

        String languageCode = profileService.getLanguageCode(chatId);
        String sentence = sentenceService.getSentence(SentenceKey.LANGUAGE, languageCode);
        sendMessage.setText(String.format(sentence, languageCode));

        sendMessage.setReplyMarkup(buttonService.getLanguagesButton(languageCode));
        sendingService.sendMessage(sendMessage);
    }

    public void help(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);


        InputFile inputFile = new InputFile();
        inputFile.setMedia("AgACAgIAAxkBAAN0ZGX-6KcwY1mcVJeXghKOSAQ70OsAArXGMRvqMjFLoz_jGKXPMEkBAAMCAAN5AAMvBA");
        sendPhoto.setPhoto(inputFile);

        String languageCode = profileService.getLanguageCode(chatId);

        String sentence = sentenceService.getSentence(SentenceKey.HELP, languageCode);
        sendPhoto.setCaption(sentence);

        sendingService.sendMessage(sendPhoto);
    }


}


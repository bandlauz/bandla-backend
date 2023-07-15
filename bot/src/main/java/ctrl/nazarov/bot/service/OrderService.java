package ctrl.nazarov.bot.service;

import ctrl.nazarov.bot.bot.config.SendMessageService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.service.ButtonService;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ctrl.nazarov.bot.enums.SentenceKey;
import ctrl.nazarov.bot.enums.Step;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProfileService profileService;
    private final ButtonService buttonService;
    private final SentenceService sentenceService;
    private final SendMessageService sendingService;

    public void toOrder(Long chatId) {
        String languageCode = profileService.getLanguageCode(chatId);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(sentenceService.getSentence(SentenceKey.ORDER, languageCode));
        sendMessage.setReplyMarkup(buttonService.getOrderMarkup());

        sendingService.sendMessage(sendMessage);

        profileService.changeStep(chatId, Step.ORDER);
    }
}

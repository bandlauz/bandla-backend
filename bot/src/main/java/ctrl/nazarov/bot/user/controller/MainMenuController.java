package ctrl.nazarov.bot.user.controller;

import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.service.OrderService;
import ctrl.nazarov.bot.service.ProfileEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ctrl.nazarov.bot.enums.ButtonKey;


@Component
@RequiredArgsConstructor
public class MainMenuController {

    private final SentenceService sentenceService;
    private final ProfileEditService cabinetService;

    private final OrderService orderService;

    public void handle(String text, Message message) {
        ButtonKey buttonKey = sentenceService.getButtonKey(text);
        Long chatId = message.getChatId();

        switch (buttonKey) {
            case ORDER -> orderService.toOrder(chatId);
            case PROFILE -> cabinetService.toEditCabinet(chatId);
        }
    }
}

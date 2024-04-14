package uz.bandla.telegrambot.handler;

import uz.bandla.annotations.Handler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@Handler
@RequiredArgsConstructor
public class MessageHandler{
    private final TextHandler textHandler;
    private final ContactHandler contactHandler;
    private final PassportDataHandler passportDataHandler;

    public void handle(Message message) {
        if (message.hasText()) {
            textHandler.handle(message);
            return;
        }

        if (message.hasPassportData()) {
            passportDataHandler.handle(message);
            return;
        }

        if (message.hasContact()) {
            contactHandler.handle(message);
        }
    }
}
package uz.bandla.telegrambot.handler;

import uz.bandla.telegrambot.handler.interfaces.Handler;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class MessageHandler implements Handler<Message> {
    private final TextHandler textHandler;
    private final ContactHandler contactHandler;

    @Override
    public void handle(Message message) {
        if (message.hasText()) {
            textHandler.handle(message);
            return;
        }

        if (message.hasContact()) {
            contactHandler.handle(message);
        }
    }
}
package uz.bandla.telegrambot.handler;

import uz.bandla.annotations.Handler;

import lombok.RequiredArgsConstructor;
import io.github.nazarovctrl.telegrambotspring.controller.AbstractUpdateController;
import org.telegram.telegrambots.meta.api.objects.Update;

@Handler
@RequiredArgsConstructor
public class UpdateHandler extends AbstractUpdateController {
    private final MessageHandler messageHandler;

    @Override
    public void handle(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage());
        }
    }
}
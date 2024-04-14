package uz.bandla.telegrambot.handler;

import uz.bandla.annotations.Handler;
import uz.bandla.telegrambot.service.MessageSenderService;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Handler
@RequiredArgsConstructor
public class TextHandler {

    private final MessageSenderService messageSenderService;

    public void handle(Message message) {
        String text = message.getText();

        if (text.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("""
                    Salom🙂
                    bandla.uz sahifasi sizga eng yaqin bo'lgan ⚽futbol maydonini
                    band qilishda yordam bera oladigan yangi loyiha""");
            messageSenderService.send(sendMessage);
        }
    }
}
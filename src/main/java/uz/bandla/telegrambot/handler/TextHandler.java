package uz.bandla.telegrambot.handler;

import uz.bandla.telegrambot.handler.interfaces.Handler;
import uz.bandla.telegrambot.service.MessageSenderService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class TextHandler implements Handler<Message> {

    @Value("#{'${bot.commands}'.split(',')}")
    private List<String> botCommands;

    private final MessageSenderService messageSenderService;

    @Override
    public void handle(Message message) {
        String text = message.getText();
        if (botCommands.stream().anyMatch(command -> command.equals(text.toLowerCase()))) {
            replyToCommand(text.toLowerCase(), message.getChatId());
        }
    }

    private void replyToCommand(String command, long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (command.equals("/start")) {
            sendMessage.setText("""
                    Salom🙂
                    bandla.uz sahifasi sizga eng yaqin bo'lgan ⚽futbol maydonini
                    band qilishda yordam bera oladigan yangi loyiha""");
            messageSenderService.send(sendMessage);
        }
    }
}
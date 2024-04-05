package uz.bandla.telegrambot.service.impl;

import uz.bandla.telegrambot.service.MessageSenderService;

import io.github.nazarovctrl.telegrambotspring.bot.MessageSender;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {
    private final MessageSender messageSender;

    public MessageSenderServiceImpl(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public Message send(SendMessage sendMessage) {
        try {
            return messageSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

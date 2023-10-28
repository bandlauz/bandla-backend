package uz.bandla.telegrambot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageSenderService {
    Message send(SendMessage sendMessage);
}

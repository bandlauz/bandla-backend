package uz.bandla.telegrambot.handler.impl;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;

import org.telegram.telegrambots.meta.api.objects.Message;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.entity.TelegramUserEntity;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.favor.TelegramUserFavor;
import uz.bandla.telegrambot.handler.Handler;
import uz.bandla.telegrambot.service.MessageSenderService;
import uz.bandla.telegrambot.util.ButtonUtil;

import java.util.Optional;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class MessageHandlerImpl implements Handler<Message> {
    private final ProfileFavor profileFavor;
    private final TelegramUserFavor telegramUserFavor;

    private final MessageSenderService messageSenderService;

    @Override
    public void handle(Message message) {
        if (message.hasContact()) {
            getPhoneNumber(message);
        }
    }

    private void getPhoneNumber(Message message) {
        Contact contact = message.getContact();

        Optional<TelegramUserEntity> optional = telegramUserFavor.findById(message.getChatId());
        if (optional.isEmpty()) {
            return;
        }

        TelegramUserEntity telegramUser = optional.get();
        telegramUser.setPhoneNumber(contact.getPhoneNumber());

        ProfileEntity profile = new ProfileEntity(telegramUser.getFirstName(), telegramUser.getLastName(), telegramUser.getPhoneNumber(), telegramUser.getPhotoUrl());
        profileFavor.save(profile);

        telegramUser.setProfile(profile);
        telegramUserFavor.save(telegramUser);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Success");
        sendMessage.setReplyMarkup(ButtonUtil.getKeyboardRemove());
        sendMessage.setChatId(message.getChatId());

        messageSenderService.send(sendMessage);
    }
}

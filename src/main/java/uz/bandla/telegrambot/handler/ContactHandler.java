package uz.bandla.telegrambot.handler;

import uz.bandla.entity.ProfileEntity;
import uz.bandla.entity.TelegramUserEntity;
import uz.bandla.favor.ProfileFavor;
import uz.bandla.favor.TelegramUserFavor;
import uz.bandla.telegrambot.handler.interfaces.Handler;
import uz.bandla.telegrambot.service.MessageSenderService;
import uz.bandla.telegrambot.util.ButtonUtil;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@uz.bandla.annotations.Handler
@RequiredArgsConstructor
public class ContactHandler implements Handler<Message> {
    private final ProfileFavor profileFavor;
    private final TelegramUserFavor telegramUserFavor;
    private final MessageSenderService messageSenderService;

    @Override
    public void handle(Message message) {
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
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("""
                Muvaffaqiyatli ro‘yxatdan o‘tdingiz!
                bandla.uz sahifasiga qaytib yana bir bor
                telegram orqali kirishga bosing""");
        sendMessage.setReplyMarkup(ButtonUtil.getKeyboardRemove());
        messageSenderService.send(sendMessage);
    }
}
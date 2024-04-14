package uz.bandla.telegrambot.handler;

import uz.bandla.annotations.Handler;
import uz.bandla.entity.ProfileEntity;
import uz.bandla.entity.TelegramUserEntity;
import uz.bandla.repository.ProfileRepository;
import uz.bandla.repository.TelegramUserRepository;
import uz.bandla.telegrambot.service.MessageSenderService;
import uz.bandla.telegrambot.util.ButtonUtil;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Handler
@RequiredArgsConstructor
public class ContactHandler {
    private final ProfileRepository profileRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final MessageSenderService messageSenderService;

    public void handle(Message message) {
        Contact contact = message.getContact();

        Optional<TelegramUserEntity> optional = telegramUserRepository.findById(message.getChatId());
        if (optional.isEmpty()) {
            return;
        }

        TelegramUserEntity telegramUser = optional.get();
        telegramUser.setPhoneNumber(contact.getPhoneNumber());

        ProfileEntity profile = new ProfileEntity(telegramUser.getFirstName(), telegramUser.getLastName(), telegramUser.getPhoneNumber(), telegramUser.getPhotoUrl());
        profileRepository.save(profile);

        telegramUser.setProfile(profile);
        telegramUserRepository.save(telegramUser);

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
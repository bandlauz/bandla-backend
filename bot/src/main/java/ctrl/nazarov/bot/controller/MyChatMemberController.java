package ctrl.nazarov.bot.controller;

import ctrl.nazarov.bot.bot.config.SendMessageService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.ChatType;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import ctrl.nazarov.bot.enums.ChatRole;
import ctrl.nazarov.bot.enums.SentenceKey;

@Component
@RequiredArgsConstructor
public class MyChatMemberController {
    private final SendMessageService sendingService;

    private final SentenceService sentenceService;
    private final ProfileService profileService;


    public void handle(ChatMemberUpdated myChatMember) {
        Chat chat = myChatMember.getChat();
        if (chat.getType().equals(ChatType.PRIVATE.name().toLowerCase())) {
            userChatController(myChatMember);
        }
    }

    private void userChatController(ChatMemberUpdated myChatMember) {


        ChatRole role = ChatRole.valueOf(myChatMember.getNewChatMember().getStatus().toUpperCase());
        Long userId = myChatMember.getFrom().getId();

        if (role.equals(ChatRole.KICKED)) {
            profileService.changeVisibleByUserId(userId, false);
            return;
        }

        if (role.equals(ChatRole.MEMBER)) {
            String languageCode = profileService.getLanguageCode(userId);
            profileService.changeVisibleByUserId(userId, true);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(sentenceService.getSentence(SentenceKey.RESTART, languageCode));
            sendMessage.setChatId(userId);
            sendingService.sendMessage(sendMessage);
        }
    }


}
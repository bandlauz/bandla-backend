package ctrl.nazarov.bot.controller;

import ctrl.nazarov.bot.admin.controller.AdminMessageController;
import ctrl.nazarov.bot.entity.ProfileEntity;
import ctrl.nazarov.bot.enums.ProfileRole;
import ctrl.nazarov.bot.service.ProfileService;
import ctrl.nazarov.bot.user.controller.CallBackController;
import ctrl.nazarov.bot.user.controller.MessageController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateController {
    private final MessageController messageController;
    private final AdminMessageController adminMessageController;
    private final CallBackController callBackController;
    private final MyChatMemberController myChatMemberController;
    private final ProfileService profileService;

    public void handle(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (checkToAdmin(message.getChatId())) {
                adminMessageController.handle(message);
            } else {
                messageController.handle(message);
            }
            return;
        }

        if (update.hasCallbackQuery()) {
            callBackController.handle(update.getCallbackQuery());
            return;
        }


        if (update.hasMyChatMember()) {
            myChatMemberController.handle(update.getMyChatMember());
        }

    }

    private boolean checkToAdmin(Long chatId) {
        ProfileEntity byUserId = profileService.getByUserId(chatId);
        if (byUserId == null) {
            return false;
        }
        return ProfileRole.ADMIN.equals(byUserId.getRole());
    }
}

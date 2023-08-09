package ctrl.nazarov.bot.user.client.controller;

import ctrl.nazarov.bot.bot.config.BotConfig;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.service.ProfileEditService;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

@Component
@RequiredArgsConstructor
public class MessageController {
    private final ProfileService profileService;
    private final TextController textController;
    private final ProfileEditController profileEditController;
    private final ProfileEditService profileEditService;
    private final BotConfig botConfig;

    public void handle(Message message) {
        if (profileService.addUser(message.getFrom())) {
            profileEditService.requestName(message.getChatId(), Step.PROFILE_ENTER_NAME);
            return;
        }

        if (message.hasText()) {
            if (itIsCommand(message.getText())) {
                textController.replyToBotCommand(message);
                return;
            }
            textController.handle(message);
            return;
        }

        if (message.hasContact()) {
            Step step = profileService.getStep(message.getChatId());
            if (step.equals(Step.PROFILE_EDIT_PHONE)) {
                profileEditController.changePhoneNumber(message);
            } else if (step.equals(Step.PROFILE_ENTER_PHONE)) {
                profileEditController.enterPhoneNumber(message);
            }
        }
    }

    private boolean itIsCommand(String text) {
        return botConfig.getCommandList().stream()
                .map(BotCommand::getCommand)
                .anyMatch(command -> command.equals(text));
    }

}

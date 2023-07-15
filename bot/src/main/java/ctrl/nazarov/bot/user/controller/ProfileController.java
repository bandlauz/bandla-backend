package ctrl.nazarov.bot.user.controller;

import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.service.ProfileEditService;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileEditService profileEditService;

    public void handle(Long chatId, String text) {
        Step step = profileService.getStep(chatId);

        switch (step) {
            case PROFILE_ENTER_NAME -> profileEditService.changeName(chatId, text, Step.PROFILE_ENTER_PHONE, true);
            case PROFILE_ENTER_PHONE -> profileEditService.changePhoneNumber(chatId, text, true);
        }

    }
}

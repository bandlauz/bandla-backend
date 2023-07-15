package ctrl.nazarov.bot.admin.controller;

import ctrl.nazarov.bot.admin.service.AdminTextService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.ButtonKey;
import ctrl.nazarov.bot.enums.Step;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static ctrl.nazarov.bot.enums.Step.REGION_NAME;


@Component
@RequiredArgsConstructor
public class AdminTextController {
    private final AdminTextService adminTextService;
    private final SentenceService sentenceService;
    private final ProfileService profileService;


    public void handle(String text, Long chatId) {

        ButtonKey buttonKey = sentenceService.getButtonKey(text);

        if (buttonKey != null && buttonKey.equals(ButtonKey.HOME)) {
            adminTextService.toHomePage(chatId);
            return;
        }

        if (buttonKey != null) {
            switch (buttonKey) {
                case STATISTICS -> adminTextService.sendStatistic(chatId);
                case POST_CREATE -> adminTextService.requestPost(chatId);
                case REGIONS -> adminTextService.sendRegions(chatId);
                case REGION_ADD -> adminTextService.requestRegionName(chatId);
            }
            return;

        }

        Step step = profileService.getStep(chatId);
        if (step != null) {
            switch (step) {
                case REGION_NAME -> adminTextService.addRegion(chatId, text);
            }
            return;
        }


    }

    public void replyToBotCommand(String text, Long chatId) {
        switch (text) {
            case "/start" -> adminTextService.welcome(chatId);
            case "/language" -> adminTextService.changeLanguage(chatId);
        }

    }

}

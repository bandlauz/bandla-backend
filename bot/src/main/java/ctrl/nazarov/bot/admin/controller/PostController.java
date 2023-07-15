package ctrl.nazarov.bot.admin.controller;

import ctrl.nazarov.bot.admin.service.AdminTextService;
import ctrl.nazarov.bot.admin.service.PostService;
import ctrl.nazarov.bot.bot.sentence.SentenceService;
import ctrl.nazarov.bot.enums.ButtonKey;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;


@Component
public class PostController {
    private final PostService postService;
    private final SentenceService sentenceService;
    private final AdminTextService adminTextService;

    public PostController(PostService postService, SentenceService sentenceService, AdminTextService adminTextService) {
        this.postService = postService;
        this.sentenceService = sentenceService;
        this.adminTextService = adminTextService;
    }

    public void handle(Message message) {

        if (message.hasText()) {
            ButtonKey buttonKey = sentenceService.getButtonKey(message.getText());

            if (buttonKey != null && buttonKey.equals(ButtonKey.HOME)) {
                adminTextService.toHomePage(message.getChatId());
                return;
            }
            postService.sendText(message.getText());
        }

        if (message.hasPhoto()) {
            postService.sendPhoto(message.getPhoto(), message.getCaption());
            return;
        }

        if (message.hasVideo()) {
            postService.sendVideo(message.getVideo(), message.getCaption());
            return;
        }

        if (message.hasPoll()) {
            postService.sendPoll(message.getPoll(), message.getChatId());
            return;
        }
        if (message.hasAudio()) {
            postService.sendAudio(message.getAudio(), message.getCaption());
            return;
        }
        if (message.hasVoice()) {
            postService.sendVoice(message.getVoice(), message.getCaption());
            return;
        }

        if (message.hasAnimation()) {
            postService.sendAnimation(message.getAnimation(), message.getCaption());
            return;
        }


        if (message.hasDocument()) {
            postService.sendDocument(message.getDocument(), message.getCaption());
            return;
        }

        if (message.hasLocation()) {
            postService.sendLocation(message.getLocation());
            return;
        }

        if (message.hasContact()) {
            postService.send(message);
        }

    }
}

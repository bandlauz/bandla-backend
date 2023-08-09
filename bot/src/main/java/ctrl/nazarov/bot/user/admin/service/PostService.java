package ctrl.nazarov.bot.user.admin.service;

import ctrl.nazarov.bot.bot.config.SendMessageService;
import ctrl.nazarov.bot.entity.ProfileEntity;
import ctrl.nazarov.bot.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollOption;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final ProfileService profileService;
    private final SendMessageService sendingService;


    public void sendText(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendMessage.setChatId(user.getUserId());
            sendingService.sendMessage(sendMessage);
        });


    }

    public void sendPhoto(List<PhotoSize> photo, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption(caption);
        sendPhoto.setPhoto(new InputFile(photo.get(photo.size() - 1).getFileId()));

        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendPhoto.setChatId(user.getUserId());
            sendingService.sendMessage(sendPhoto);
        });
    }


    public void sendVideo(Video video, String caption) {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setCaption(caption);
        sendVideo.setVideo(new InputFile(video.getFileId()));

        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendVideo.setChatId(user.getUserId());
            sendingService.sendMessage(sendVideo);
        });
    }

    public void sendPoll(Poll poll, Long userId) {

        Long chatId = userId;
//        ChatEntity startup_cafeuz = chatList.stream().filter(chatEntity -> chatEntity.getUsername().equals("startup_cafeuz")).findAny().orElse(null);
//        if (startup_cafeuz != null) {
//            chatId = startup_cafeuz.getChatId();
//        }

        SendPoll sendPoll = new SendPoll();
        sendPoll.setType(poll.getType());
        sendPoll.setAllowMultipleAnswers(poll.getAllowMultipleAnswers());


        List<String> options = new ArrayList<>();
        List<PollOption> pollOptions = poll.getOptions();
        pollOptions.forEach(pollOption -> {
            options.add(pollOption.getText());
        });

        sendPoll.setOptions(options);
        sendPoll.setCloseDate(poll.getCloseDate());
        sendPoll.setCorrectOptionId(poll.getCorrectOptionId());
        sendPoll.setExplanation(poll.getExplanation());
        sendPoll.setExplanationEntities(poll.getExplanationEntities());
        sendPoll.setIsAnonymous(poll.getIsAnonymous());
        sendPoll.setIsClosed(poll.getIsClosed());
        sendPoll.setOpenPeriod(poll.getOpenPeriod());
        sendPoll.setQuestion(poll.getQuestion());
        sendPoll.setChatId(chatId);
        Message message = sendingService.sendMessage(sendPoll);

        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setMessageId(message.getMessageId());
        forwardMessage.setFromChatId(message.getChatId());


        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            forwardMessage.setChatId(user.getUserId());
            sendingService.sendMessage(forwardMessage);
        });
    }

    public void sendAudio(Audio audio, String caption) {
        SendAudio sendAudio = new SendAudio();
        sendAudio.setCaption(caption);

        sendAudio.setDuration(audio.getDuration());
        sendAudio.setTitle(audio.getTitle());

        PhotoSize thumb = audio.getThumb();
        if (thumb != null) {
            InputFile inputFile = new InputFile();
            inputFile.setMedia(thumb.getFileId());
            sendAudio.setThumb(inputFile);
        }
        InputFile inputFile = new InputFile();
        inputFile.setMedia(audio.getFileId());
        sendAudio.setAudio(inputFile);
        sendAudio.setPerformer(audio.getPerformer());

        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendAudio.setChatId(user.getUserId());
            sendingService.sendMessage(sendAudio);
        });

    }

    public void sendVoice(Voice voice, String caption) {
        SendVoice sendVoice = new SendVoice();
        sendVoice.setCaption(caption);

        InputFile inputFile = new InputFile(voice.getFileId());
        sendVoice.setVoice(inputFile);
        sendVoice.setDuration(voice.getDuration());

        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendVoice.setChatId(user.getUserId());
            sendingService.sendMessage(sendVoice);
        });
    }

    public void sendAnimation(Animation animation, String caption) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setCaption(caption);
        sendAnimation.setAnimation(new InputFile(animation.getFileId()));


        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendAnimation.setChatId(user.getUserId());
            sendingService.sendMessage(sendAnimation);
        });
    }

    public void sendDocument(Document document, String caption) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setCaption(caption);
        sendDocument.setDocument(new InputFile(document.getFileId()));


        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendDocument.setChatId(user.getUserId());
            sendingService.sendMessage(sendDocument);
        });
    }

    public void sendLocation(Location location) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setLatitude(location.getLatitude());
        sendLocation.setLongitude(location.getLongitude());
        sendLocation.setLivePeriod(location.getLivePeriod());
        sendLocation.setHeading(location.getHeading());
        sendLocation.setHorizontalAccuracy(location.getHorizontalAccuracy());
        sendLocation.setProximityAlertRadius(location.getProximityAlertRadius());


        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            sendLocation.setChatId(user.getUserId());
            sendingService.sendMessage(sendLocation);
        });

    }

    public void send(Message message) {

        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setFromChatId(message.getChatId());
        forwardMessage.setMessageId(message.getMessageId());
        forwardMessage.setProtectContent(true);
        List<ProfileEntity> userList = profileService.getUserList();

        userList.forEach(user -> {
            forwardMessage.setChatId(user.getUserId());
            sendingService.sendMessage(forwardMessage);
        });

    }
}

package ctrl.nazarov.bot.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class SendMessageService {

    private TelegramBot telegramBot;

    public void setTelegramBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessage(SendDocument sendDocument) {
        try {
            telegramBot.execute(sendDocument);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(SendPhoto sendPhoto) {
        try {
            telegramBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(DeleteMessage deleteMessage) {
        try {
            telegramBot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendMessage(SendVideo sendVideo) {
        try {
            telegramBot.execute(sendVideo);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    public ChatMember sendGetChatMember(GetChatMember getChatMember) {
        try {
            return telegramBot.execute(getChatMember);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(EditMessageText editMessageText) {
        try {
            telegramBot.execute(editMessageText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(EditMessageMedia editMessageText) {
        try {
            telegramBot.execute(editMessageText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(EditMessageReplyMarkup markup) {
        try {
            telegramBot.execute(markup);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(EditMessageCaption caption) {
        try {
            telegramBot.execute(caption);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public Message sendMessage(SendPoll sendPoll) {
        try {
            return telegramBot.execute(sendPoll);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Message sendMessage(ForwardMessage forwardMessage) {
        try {
            return telegramBot.execute(forwardMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendAudio sendAudio) {
        try {
            telegramBot.execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendVoice sendVoice) {
        try {
            telegramBot.execute(sendVoice);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendAnimation sendAnimation) {
        try {
            telegramBot.execute(sendAnimation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendLocation sendLocation) {
        try {
            telegramBot.execute(sendLocation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(SendContact sendContact) {
        try {
            telegramBot.execute(sendContact);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}

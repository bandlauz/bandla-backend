package ctrl.nazarov.bot.bot.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramWebhookBot {

    private final BotConfig botConfig;


    @Bean(name = "command")
    CommandLineRunner commandLineRunner(SendMessageService sendMessageService) {
        return args -> sendMessageService.setTelegramBot(this);
    }

    @PostConstruct
    private void initializeBotCommands() {
        try {
            execute(new SetMyCommands(botConfig.commandList, new BotCommandScopeDefault(), null));
            log.info("Command list successfully initialized");
        } catch (TelegramApiException e) {
            log.warn("Command list initializing failed");
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    public String getBotUri() {
        return botConfig.getBotUri();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return "/update";
    }
}

package ctrl.nazarov.bot.bot.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

    @Value("${bot.uri}")
    String botUri;

    List<BotCommand> commandList = new ArrayList<>();

    @PostConstruct
    public void addBotCommands() {
        commandList.add(new BotCommand("/start", "get a welcome message"));
        commandList.add(new BotCommand("/help", "info how to use this bot"));
        commandList.add(new BotCommand("/language", "change language"));
    }

}

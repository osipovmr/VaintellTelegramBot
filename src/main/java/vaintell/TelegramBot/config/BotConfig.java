package vaintell.TelegramBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@Data
public class BotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;
    @Value("${bot.delay}")
    Integer delay;
    @Value("${bot.message}")
    private String welcomeMsg;

    @Value("${bot.tokenOne}")
    String botOne;
    @Value("${bot.tokenTwo}")
    String botTwo;


}

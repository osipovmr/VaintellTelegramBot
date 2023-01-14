package vaintell.TelegramBot.service.delayService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vaintell.TelegramBot.config.BotConfig;

import javax.ws.rs.BadRequestException;

@Service
@RequiredArgsConstructor
public class DelayServiceImpl implements DelayService {

    private final BotConfig botConfig;

    @Override
    public int getDelay() {
        return botConfig.getDelay();
    }

    @Override
    public void setDelay(int delay) {
        if (delay < 0 ) throw new BadRequestException("Wrong delay!");
        botConfig.setDelay(delay);
    }

    @Override
    public String getWelcomeMsg(){
        return botConfig.getWelcomeMsg();
    }
}

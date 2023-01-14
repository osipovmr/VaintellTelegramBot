package vaintell.TelegramBot.service.delayService;

import org.springframework.stereotype.Service;

@Service
public interface DelayService {
    int getDelay();

    void setDelay(int delay);

    String getWelcomeMsg();
}

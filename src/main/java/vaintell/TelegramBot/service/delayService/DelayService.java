package vaintell.TelegramBot.service.delayService;

import org.springframework.stereotype.Service;

@Service
public interface DelayService {
    String getDelay();
    void setDelay(int delay);
}

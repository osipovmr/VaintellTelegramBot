package vaintell.TelegramBot.service.delayService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
@RequiredArgsConstructor
public class DelayServiceImpl implements DelayService {

    int delay;

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public void setDelay(int delay) {
        if (delay < 0 ) throw new BadRequestException("Wrong delay!");
        this.delay = delay;
    }
}

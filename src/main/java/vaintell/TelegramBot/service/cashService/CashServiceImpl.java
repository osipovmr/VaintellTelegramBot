package vaintell.TelegramBot.service.cashService;

import org.springframework.stereotype.Service;
import vaintell.TelegramBot.config.Constants;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class CashServiceImpl implements CashService{

    HashMap<String, Integer> cash = new HashMap<>();

    @Override
    public Integer getMessageNumber(String chatId) {
        if (!cash.containsKey(chatId)) {
            cash.put(chatId, 1);
            return 1;
        }
        else {
            int count = cash.get(chatId);
            count = count + 1;
            cash.remove(chatId);
            cash.put(chatId, count);
            return count;
        }
    }
}

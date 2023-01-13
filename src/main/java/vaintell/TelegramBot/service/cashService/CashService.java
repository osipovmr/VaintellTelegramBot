package vaintell.TelegramBot.service.cashService;

import org.springframework.stereotype.Service;

@Service
public interface CashService {
    Integer getMessageNumber(String chatId);
}

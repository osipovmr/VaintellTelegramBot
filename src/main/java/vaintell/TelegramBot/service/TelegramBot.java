package vaintell.TelegramBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vaintell.TelegramBot.config.BotConfig;
import vaintell.TelegramBot.config.Constants;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return Constants.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message inMess = update.getMessage();
        System.out.println(inMess);
        String chatId = inMess.getChatId().toString();
        System.out.println(chatId);
        //SendMessage outMess = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendTextMessage(chatId, "Sorry, command was not recognized.");
            }
        }
    }

    private void startCommandReceived(String chatId, String name) {
        String answer = "Hello, " + name + "! Nice to meet you!";
        sendTextMessage(chatId, answer);
    }

    private void sendTextMessage(String chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

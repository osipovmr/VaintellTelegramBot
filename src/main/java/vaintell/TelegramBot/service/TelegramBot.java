package vaintell.TelegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vaintell.TelegramBot.config.Constants;
import vaintell.TelegramBot.service.personService.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    //private final BotConfig botConfig;
    private final PersonService personService;

    HashMap<String, List<String>> cash = new HashMap<>();

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
        String chatId = inMess.getChatId().toString();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    personService.registerPerson(chatId, inMess.getChat().getFirstName(), inMess.getChat().getLastName());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    try {
                        TimeUnit.SECONDS.sleep(Constants.sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!cash.containsKey(chatId)) {
                        List<String> userMessages = new ArrayList<>();
                        userMessages.add(inMess.getText());
                        cash.put(chatId, userMessages);
                        System.out.println(cash);
                    }
                    else {
                        List<String> list = cash.get(chatId);
                        list.add(inMess.getText());
                        System.out.println(cash);
                    }

                    sendTextMessage(chatId, inMess.getText());
            }
        }
    }

    private void startCommandReceived(String chatId, String name) {
        String answer = "Hello, " + name + "! Nice to meet you!";
        sendTextMessage(chatId, answer);
    }

    private void sendTextMessage(String chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        List<String> list = cash.get(chatId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend + " " + list.size());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

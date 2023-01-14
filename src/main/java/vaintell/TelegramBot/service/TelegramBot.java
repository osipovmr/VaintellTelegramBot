package vaintell.TelegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vaintell.TelegramBot.config.BotConfig;
import vaintell.TelegramBot.service.cashService.CashService;
import vaintell.TelegramBot.service.personService.PersonService;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final PersonService personService;
    private final CashService cashService;

    @Override
    public String getBotUsername() {
        System.out.println(botConfig.getName());
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        System.out.println(botConfig.getToken());
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String delay = botConfig.getDelay();
        Integer delayInt = Integer.valueOf(delay);
        Message inMess = update.getMessage();
        String chatId = inMess.getChatId().toString();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                /**
                 * 1) При запуске бота пользователь вводит команду /start. В этот момент сервис должен "запоминать" пользователя
                 */
                case "/start":
                    personService.registerPerson(chatId, inMess.getChat().getFirstName(), inMess.getChat().getLastName());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                /**
                 * 2) Весь дальнейший ввод информации, после начала использования бота и сохранения пользователя, считается как echo message.
                 */
                default:
                    try {
                        TimeUnit.MILLISECONDS.sleep(delayInt);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sendTextMessage(chatId, inMess.getText());
            }
        }
    }

    private void startCommandReceived(String chatId, String name) {
        String answer = "Hello, " + name + "! Nice to meet you!";
        sendTextMessage(chatId, answer);
    }

    /**
     * 7) После приема запроса добавляется индекс номера сообщение,
     * производится повторная задержка со временем delay и отправляет измененное сообщение пользователю
     * @param chatId
     * @param textToSend
     */
    private void sendTextMessage(String chatId, String textToSend) {
        String delay = botConfig.getDelay();
        Integer delayInt = Integer.valueOf(delay);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend + " " + cashService.getMessageNumber(chatId));
        try {
            TimeUnit.MILLISECONDS.sleep(delayInt);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

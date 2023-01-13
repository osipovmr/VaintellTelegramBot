package vaintell.TelegramBot.service.personService;

import org.springframework.stereotype.Service;

@Service
public interface PersonService {

    void registerPerson(String chatId, String firstName, String lastName);
}

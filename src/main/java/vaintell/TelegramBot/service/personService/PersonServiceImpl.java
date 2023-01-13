package vaintell.TelegramBot.service.personService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vaintell.TelegramBot.model.Person;
import vaintell.TelegramBot.repository.PersonRepository;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public void registerPerson(String chatId, String firstName, String lastName) {
        Person checkPerson = personRepository.findByChatId(chatId);
        if (isNull(checkPerson)) {
            Person person = new Person();
            person.setChatId(chatId);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            personRepository.save(person);
        }
    }
}

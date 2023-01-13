package vaintell.TelegramBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vaintell.TelegramBot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByChatId(String chatId);
}

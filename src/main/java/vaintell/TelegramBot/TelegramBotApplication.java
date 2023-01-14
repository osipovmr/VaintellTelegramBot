package vaintell.TelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
		System.out.println("Gradle command line arguments example");
		for (String arg : args) {
			System.out.println("Got argument [" + arg + "]");
		}
	}

}

# Тестовое задание от компании ООО Виэйинтеллидженс на позицию Программист-разработчик Java Middle.

---
#### Создан TelegramBot EchoBot на базе Spring и TelegramLongPollingBot.

TelegramBot возвращает пользователю сообщение через задержку времени с добавлением в конце порядкового номера сообщения пользователя.
Через web-интерфейс возможно менять время задержки "/updateQueueDelay" и проверять текущее значение "/getDelay".

---
Применены технологии:
- Java 8
- SpringBoot
- Java Persistence API
- PostgreSQL
- Gradle
---
База данных PostgreSQL. Таблица Person создана через Liquibase.
Перед запуском приложения из консоли требуется ввести параметры БД для подключения в application.properties.

---
Запуск приложения из консоли поддерживает несколько аргументов.
Пример команды:
./gradlew bootRun --args="--bot.token=5777064796:AAHwRDsxtAvF2BI93VrCJ7m97znFHW3We6U --bot.delay=1234"

---
Дата выполнения 14.01.2023.


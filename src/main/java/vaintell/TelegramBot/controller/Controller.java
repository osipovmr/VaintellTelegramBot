package vaintell.TelegramBot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaintell.TelegramBot.model.Delay;
import vaintell.TelegramBot.service.delayService.DelayService;


@RestController
@RequiredArgsConstructor
public class Controller {

    private final DelayService delayService;

    @GetMapping("/helloWorld")
    public ResponseEntity<String> checkTest(){
        return ResponseEntity.ok("Hello, world!");
    }

    @GetMapping("/getDelay")
    public ResponseEntity<String> getDelay(){
        return ResponseEntity.ok(delayService.getDelay());
    }

    /**
     * 8) Веб интерфейс должен иметь функцию обновления времени задержки delay
     * Сервер возвращает статус 200 в случае успешной обработки. В случае неуспешной обработки возвращате статус 500
     * @param delay
     * @return
     */
    @PutMapping("/updateQueueDelay")
    public ResponseEntity<Void> updateQueueDelay(@RequestBody Delay delay){
        delayService.setDelay(delay.getDelay());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

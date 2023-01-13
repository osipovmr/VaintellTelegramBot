package vaintell.TelegramBot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/helloWorld")
    public ResponseEntity<String> checkTest(){
        return ResponseEntity.ok("Hello, world!");
    }
}

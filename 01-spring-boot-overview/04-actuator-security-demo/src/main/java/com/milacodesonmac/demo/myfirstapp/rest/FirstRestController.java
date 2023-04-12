package com.milacodesonmac.demo.myfirstapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
    @GetMapping("/")

    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/workout")

    public String getDailyWorkout() {
        return "Run a hard 5k daily!";
    }

    @GetMapping("/fortune")

    public String getDailyFortune() {
        return "Today is your lucky day!";
    }
}

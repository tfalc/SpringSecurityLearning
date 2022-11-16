package tfalc.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "Welcome to my Spring Security App";
    }

    @GetMapping("/users")
    public String users(){
        return "All users authorized";
    }

    @GetMapping("/managers")
    public String managers(){
        return "All managers authorized";
    }
}

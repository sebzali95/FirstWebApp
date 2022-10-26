package com.example.firstwebapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/student/welcome")
    public String welcomePage() {
        return "Welcome";
    }
}

package com.example.moviesearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("value", "Helloooooooooo");
        return "greeting";
    }
    
}

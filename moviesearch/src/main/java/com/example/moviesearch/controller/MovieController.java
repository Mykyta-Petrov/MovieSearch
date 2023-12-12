package com.example.moviesearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moviesearch.model.SubmitFormRequest;

@Controller
public class MovieController {
    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("request", new SubmitFormRequest());
        return "searchform";
    }
    
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute SubmitFormRequest request, Model model) {
        model.addAttribute("result", request);
        return "searchresult";
    }
    
}

package com.example.moviesearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moviesearch.model.SubmitFormRequest;
import com.example.moviesearch.service.ApiService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Controller
public class MovieController {
    private final ApiService apiService;

    public MovieController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("request", new SubmitFormRequest());
        return "searchform";
    }
    
    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute("request") SubmitFormRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "searchform";
        }

        //model.addAttribute("result", request);
        //return "searchresult";

        try {
            Mono<String> dataMono = apiService.fetch(request);
            model.addAttribute("dataMono", dataMono);
            return "movieresult";
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
}

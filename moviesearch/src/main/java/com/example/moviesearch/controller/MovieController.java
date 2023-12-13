package com.example.moviesearch.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moviesearch.model.SubmitFormRequest;
import com.example.moviesearch.service.ApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

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

        try {
            String data = apiService.fetch(request);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonData = mapper.readValue(data, new TypeReference<Map<String, Object>>(){});
            model.addAttribute("data", jsonData);
            return "searchresult";
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
}

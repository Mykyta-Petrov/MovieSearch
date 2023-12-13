package com.example.moviesearch.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moviesearch.model.SubmitFormRequest;
import com.example.moviesearch.service.ApiService;

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
    
    @GetMapping("/movie")
    public String searchQuery(@RequestParam("title") String title, @RequestParam("type") String type, @RequestParam("year") int year, Model model) {
        try {
            SubmitFormRequest request = new SubmitFormRequest();
            request.setTitle(title);
            request.setType(SubmitFormRequest.Types.valueOf(type));
            request.setYear(year);

            Map<String, Object> data = apiService.fetch(request, 't');
            model.addAttribute("data", data);
            return "moviedetails";
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute("request") SubmitFormRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "searchform";
        }

        try {
            Map<String, Object> data = apiService.fetch(request, 's');
            model.addAttribute("data", data);
            return "searchresult";
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
}

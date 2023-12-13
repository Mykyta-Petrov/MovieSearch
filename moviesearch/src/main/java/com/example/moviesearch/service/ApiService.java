package com.example.moviesearch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviesearch.model.SubmitFormRequest;

@Service
public class ApiService {
    private final String apiUrl = "http://www.omdbapi.com/";

    private final String apiKey = "9c230d35";

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String fetch(SubmitFormRequest request) {
        String yearQuery = "";

        if (request.getYear() > 0) {
            yearQuery = "&y=" + request.getYear();
        }

        String uri = String.format("%s/?apiKey=%s&s=%s&type=%s%s", apiUrl, apiKey, request.getTitle(), request.getType(), yearQuery);

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}

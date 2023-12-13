package com.example.moviesearch.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.moviesearch.model.SubmitFormRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
    private final String apiUrl = "http://www.omdbapi.com/";

    private final String apiKey = "9c230d35";

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Map<String, Object> fetch(SubmitFormRequest request, Character mode) throws JsonMappingException, JsonProcessingException {
        String yearQuery = request.getYear() > 0 ? "&y=" + request.getYear() : "";
        String plotQuery = mode == 't' ? "&plot=full" : "";
        String uri = String.format("%s/?apiKey=%s&%c=%s&type=%s%s%s", apiUrl, apiKey, mode, request.getTitle(), request.getType(), yearQuery, plotQuery);

        String data = webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(data, new TypeReference<Map<String, Object>>(){});
    }
}

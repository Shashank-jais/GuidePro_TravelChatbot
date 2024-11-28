package com.example.Chatbot.GuidePro_Backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class PlaceID_PlaceDetails {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public PlaceID_PlaceDetails(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://google-map-places.p.rapidapi.com/maps/").build();
        this.objectMapper = objectMapper;
    }

    public String fetchPlaceId(String query) {
        System.out.println("Fetching Place ID for  Place Details query: " + query); // Print query

        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/geocode/json")
                        .queryParam("address", query)
                        .queryParam("language", "en")
                        .queryParam("region", "en")
                        .queryParam("result_type", "administrative_area_level_1")
                        .queryParam("location_type", "APPROXIMATE")
                        .build())
                .header("x-rapidapi-key", "eb7bf787e5msh48901167544884dp1042b2jsn171d24114325")
                .header("x-rapidapi-host", "google-map-places.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);

        // Parse the response with Jackson
        return response.map(apiResponse -> {
            System.out.println("Received API Response: " + apiResponse); // Print the API response
            try {
                return parsePlaceID(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage()); // Print error message
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private String parsePlaceID(String apiResponse) throws IOException {
        System.out.println("Parsing Place ID from API response."); // Log the process

        // Use Jackson to read the API response
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode resultsNode = rootNode.get("results"); // Corrected: access "results" array

        if (resultsNode == null || !resultsNode.isArray() || resultsNode.isEmpty()) {
            System.out.println("No location found in the response."); // Print no location message
            return null;
        }

        // Extract the required fields from the first result in the "results" array
        JsonNode firstLocationNode = resultsNode.get(0); // Corrected: get first item in "results" array

        String placeId = null;
        if (firstLocationNode.has("place_id")) {
            placeId = firstLocationNode.get("place_id").asText();
        }

        System.out.println("Place ID: " + placeId);
        return placeId;
    }
}

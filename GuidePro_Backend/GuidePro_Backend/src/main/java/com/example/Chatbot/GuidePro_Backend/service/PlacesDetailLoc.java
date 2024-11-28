package com.example.Chatbot.GuidePro_Backend.service;

import com.example.Chatbot.GuidePro_Backend.model.PlacesDetailDTO;
import com.example.Chatbot.GuidePro_Backend.model.PlacesDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Stack;

@Service
public class PlacesDetailLoc {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public PlacesDetailLoc(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://google-map-places.p.rapidapi.com/maps/").build();
        this.objectMapper = objectMapper;
    }

    public PlacesDetails fetchPlaceDetails(String place_Id) {
        System.out.println("Fetching the place details for place Id : " + place_Id);
//        String query = "place_id="+ place_Id +"&region=en&fields=all&language=en&reviews_no_translations=true";
        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/place/details/json")
                        .queryParam("place_id", place_Id)
                        .queryParam("region", "en")
                        .queryParam("fields", "all")
                        .queryParam("language", "en")
                        .queryParam("reviews_no_translations", "true")
                        .build())
                .header("x-rapidapi-key", "eb7bf787e5msh48901167544884dp1042b2jsn171d24114325")
                .header("x-rapidapi-host", "google-map-places.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);

        // Parse the response with Jackson
        return response.map(apiResponse -> {
//            System.out.println("Received API Response: " + apiResponse); // Print the API response
            try {
                return parseDetails(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage()); // Print error message
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private PlacesDetails parseDetails(String apiResponse) throws IOException {
        PlacesDetails placesDetails = new PlacesDetails();

        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode resultNode = rootNode.path("result");

        // Validate resultNode
        if (resultNode.isMissingNode() || !resultNode.isObject()) {
            System.out.println("No place details found in the response.");
            return placesDetails; // Return empty details
        }

        // Map resultNode to DTO
        PlacesDetailDTO detail = new PlacesDetailDTO();
        detail.setAdd(resultNode.path("formatted_address").asText(""));
        detail.setLat(resultNode.path("geometry").path("location").path("lat").asDouble(0.0));
        detail.setLon(resultNode.path("geometry").path("location").path("lng").asDouble(0.0));
        detail.setLink(resultNode.path("url").asText(""));
        detail.setPlace_id(resultNode.path("place_id").asText(""));

        placesDetails.setDetails(detail);
        return placesDetails;

    }
}


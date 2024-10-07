package com.example.Chatbot.GuidePro_Backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class LocationIDLoc {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public LocationIDLoc(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://tripadvisor16.p.rapidapi.com").build();
        this.objectMapper = objectMapper;
    }

    public String fetchLocationIDByQuery(String query) {
        System.out.println("Fetching location ID for query: " + query); // Print query

        // Fetch the raw API response
        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/restaurant/searchLocation")
                        .queryParam("query", query)
                        .build())
                .header("x-rapidapi-key", "08440b8a3cmshf1dc14626335561p1977dejsn2587ce6ade42")
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);

        // Parse the response with Jackson
        return response.map(apiResponse -> {
//            System.out.println("Received API Response: " + apiResponse); // Print the API response
            try {
                return parseLocationID(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage()); // Print error message
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private String parseLocationID(String apiResponse) throws IOException {
        System.out.println("Parsing location ID from API response."); // Log the process

        // Use Jackson to read the API response
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode dataNode = rootNode.get("data"); // Get the 'data' array

        if (dataNode == null || !dataNode.isArray() || dataNode.isEmpty()) {
            System.out.println("No location found in the response."); // Print no location message
            return null;
        }

        // Extract the required fields from the first result in the 'data' array
        JsonNode firstLocationNode = dataNode.get(0); // Assuming we want the first location result

        // Extract locationId
        String locationId = null;
        if (firstLocationNode.has("locationId")) {
            locationId = firstLocationNode.get("locationId").asText();
        }

        // Extract localizedName
        String localizedName = null;
        if (firstLocationNode.has("localizedName")) {
            localizedName = firstLocationNode.get("localizedName").asText();
        }

        // Log the extracted values
        System.out.println("Location ID: " + locationId);
        System.out.println("Localized Name: " + localizedName);

        // Return the desired values (you could also return an object if multiple values are needed)
//        return "Location ID: " + locationId + ", Localized Name: " + localizedName;
        return locationId;
    }

}

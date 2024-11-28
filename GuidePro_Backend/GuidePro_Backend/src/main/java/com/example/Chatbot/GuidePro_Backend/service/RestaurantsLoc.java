package com.example.Chatbot.GuidePro_Backend.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.Chatbot.GuidePro_Backend.model.Restaurant;
import com.example.Chatbot.GuidePro_Backend.model.RestaurantDetailsDTO;
import com.example.Chatbot.GuidePro_Backend.model.RestaurantDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantsLoc {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public RestaurantsLoc(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://tripadvisor16.p.rapidapi.com").build();
        this.objectMapper = objectMapper;
    }

    public Restaurant fetchRestaurantsByLocationId(String locationId) {
        System.out.println("Fetching restaurants for location ID: " + locationId); // Print location ID

        // Fetch the raw API response
        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/restaurant/searchRestaurants")
                        .queryParam("locationId", locationId)
                        .build())
                .header("x-rapidapi-key", "d341daaad1msh28d53b20cce3c19p1b9b3djsn949ce331ef6b")
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);

        // Parse the response with Jackson
        return response.map(apiResponse -> {
//            System.out.println("Received API Response: " + apiResponse); // Print the API response
            try {
                return parseRestaurants(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage()); // Print error message
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private Restaurant parseRestaurants(String apiResponse) throws IOException {
//        System.out.println("Received API Response: " + apiResponse); // Print the raw API response
        Restaurant restaurantResponseDto = new Restaurant();
        List<RestaurantDto> restaurants = new ArrayList<>();

        // Use Jackson to read the API response
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode dataNode = rootNode.get("data").get("data"); // Adjusted to get the nested data array

        // Check if dataNode is null or not an array
        if (dataNode == null || !dataNode.isArray()) {
            System.out.println("No restaurants found in the response."); // Print no restaurants message
            return restaurantResponseDto; // Return empty restaurant response
        }

        // Check if the array is empty
        if (dataNode.isEmpty()) {
            System.out.println("No restaurants found in the response."); // Print no restaurants message
            return restaurantResponseDto; // Return empty restaurant response
        }

        // Process the array of restaurant data
        for (JsonNode restaurantNode : dataNode) {
            RestaurantDto restaurant = new RestaurantDto();

            if(restaurantNode.has("restaurantsId")){
                restaurant.setRestaurantsId(restaurantNode.get("restaurantsId").asText());
            }
            // Extract fields safely, adding checks
            if (restaurantNode.has("name")) {
                restaurant.setName(restaurantNode.get("name").asText());
            }
            if (restaurantNode.has("averageRating")) {
                restaurant.setAverageRating(restaurantNode.get("averageRating").asInt());
            }
            if (restaurantNode.has("establishmentTypeAndCuisineTags")) {
                restaurant.setEstablishmentTypeAndCuisineTags(objectMapper.convertValue(
                        restaurantNode.get("establishmentTypeAndCuisineTags"), List.class));
            }
            if (restaurantNode.has("heroImgUrl")) {
                restaurant.setHeroImgUrl(restaurantNode.get("heroImgUrl").asText());
            }
            if (restaurantNode.has("heroImgRawHeight")) {
                restaurant.setHeroImgRawHeight(restaurantNode.get("heroImgRawHeight").asInt());
            }
            if (restaurantNode.has("heroImgRawWidth")) {
                restaurant.setHeroImgRawWidth(restaurantNode.get("heroImgRawWidth").asInt());
            }

            restaurants.add(restaurant);
        }

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found in the response after parsing."); // Additional check after parsing
        }

        restaurantResponseDto.setRestaurants(restaurants);
        return restaurantResponseDto;
    }



}

package com.example.Chatbot.GuidePro_Backend.service;

import com.example.Chatbot.GuidePro_Backend.model.Hotel;
import com.example.Chatbot.GuidePro_Backend.model.HotelDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelsLoc {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public HotelsLoc(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://tripadvisor16.p.rapidapi.com").build();
        this.objectMapper = objectMapper;
    }

    public Hotel fetchHotelByGeoId(String geoId) {
        System.out.println("Fetching Hotels for GeoID: " + geoId);

        // Fetch the raw API response
        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/hotels/searchHotels")
                        .queryParam("geoId", geoId)
                        .queryParam("checkIn", "2024-11-07")
                        .queryParam("checkOut", "2024-11-10")
                        .queryParam("pageNumber", "1")
                        .queryParam("currencyCode", "INR")
                        .build())
                .header("x-rapidapi-key", "eb7bf787e5msh48901167544884dp1042b2jsn171d24114325")
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);

        return response.map(apiResponse -> {
//            System.out.println("Received API Response: " + apiResponse); // Print the API response
            try {
                return parseHotels(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage()); // Print error message
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private Hotel parseHotels(String apiResponse) throws IOException {
//        System.out.println("Received API Response: " + apiResponse); // Print the raw API response

        Hotel hotelResponseDto = new Hotel();
        List<HotelDTO> hotels = new ArrayList<>();


        // Use Jackson to read the API response
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode dataNode = rootNode.get("data").get("data"); // Adjusted to get the nested data array

        // Check if dataNode is null or not an array
        if (dataNode == null || !dataNode.isArray()) {
            System.out.println("No restaurants found in the response."); // Print no restaurants message
            return hotelResponseDto; // Return empty restaurant response
        }

        for (JsonNode hotelNode : dataNode) {
            HotelDTO hotel = new HotelDTO();
            if (hotelNode.has("id")) {
                hotel.setId(hotelNode.get("id").asText());
            }
            if (hotelNode.has("title")) {
                hotel.setTitle(hotelNode.get("title").asText());
            }

            if (hotelNode.has("primaryInfo")) {
                hotel.setPrimaryInfo(hotelNode.get("primaryInfo").asText());
            }

            if (hotelNode.has("bubbleRating")) {
                JsonNode bubbleRatingNode = hotelNode.get("bubbleRating");
                if (bubbleRatingNode.has("rating")) {
                    hotel.setRating(bubbleRatingNode.get("rating").asDouble());
                }
            }


            if (hotelNode.has("priceForDisplay")) {
                hotel.setPriceForDisplay(hotelNode.get("priceForDisplay").asText());
            }

            if (hotelNode.has("priceDetails")) {
                hotel.setPriceDetails(hotelNode.get("priceDetails").asText());
            }

            if (hotelNode.has("commerceInfo")) {
                JsonNode commerceInfoNode = hotelNode.get("commerceInfo");
                if (commerceInfoNode.has("externalUrl")) {
                    hotel.setExternalUrl(commerceInfoNode.get("externalUrl").asText());
                }
            }


            hotels.add(hotel);
        }
        if (hotels.isEmpty()) {
            System.out.println("No Hotel found in the response after parsing."); // Additional check after parsing

        }
        hotelResponseDto.setHotels(hotels);
        return hotelResponseDto;
    }
}

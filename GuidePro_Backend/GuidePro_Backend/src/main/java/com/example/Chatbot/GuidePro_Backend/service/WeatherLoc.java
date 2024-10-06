package com.example.Chatbot.GuidePro_Backend.service;

import com.example.Chatbot.GuidePro_Backend.model.Weather;
import com.example.Chatbot.GuidePro_Backend.model.WeatherDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class WeatherLoc {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public WeatherLoc(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("http://api.weatherapi.com").build();
        this.objectMapper = objectMapper;
    }

    public Weather fetchWeatherByCity(String city) {
        System.out.println("Fetching weather data for city: " + city);

        // Fetch the raw API response
        Mono<String> response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/current.json")
                        .queryParam("key", "2dbafb054c00442695f134909240610")
                        .queryParam("q", city)
                        .queryParam("aqi", "yes")
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        return response.map(apiResponse -> {
            try {
                return parseWeather(apiResponse);
            } catch (IOException e) {
                System.err.println("Error parsing response: " + e.getMessage());
                throw new RuntimeException("Failed to parse response", e);
            }
        }).block();
    }

    private Weather parseWeather(String apiResponse) throws IOException {
        Weather weatherResponseDto = new Weather();
        WeatherDTO weatherDTO = new WeatherDTO();

        // Use Jackson to read the API response
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        JsonNode locationNode = rootNode.get("location");
        JsonNode currentNode = rootNode.get("current");

        // Extract location information
        if (locationNode != null) {
            weatherDTO.setLocation_name(locationNode.get("name").asText());
            weatherDTO.setRegion(locationNode.get("region").asText());
            weatherDTO.setCountry(locationNode.get("country").asText());
        }

        // Extract current weather information
        if (currentNode != null) {
            weatherDTO.setTemp_c(currentNode.get("temp_c").asDouble());
            weatherDTO.setFeelslike_c(currentNode.get("feelslike_c").asDouble());
            weatherDTO.setHumidity(currentNode.get("humidity").asInt());
            weatherDTO.setWind_dir(currentNode.get("wind_dir").asText());
            weatherDTO.setWind_kph(currentNode.get("wind_kph").asDouble());
            weatherDTO.setCondition_text(currentNode.get("condition").get("text").asText());
            weatherDTO.setPrecip_mm(currentNode.get("precip_mm").asDouble());
            weatherDTO.setVis_km(currentNode.get("vis_km").asDouble());
            weatherDTO.setUv(currentNode.get("uv").asInt());

            // Extract air quality information if available
            if (currentNode.has("air_quality")) {
                JsonNode airQualityNode = currentNode.get("air_quality");
                weatherDTO.setPm2_5(airQualityNode.get("pm2_5").asDouble());
                weatherDTO.setUsEpaIndex(airQualityNode.get("us-epa-index").asInt());
                weatherDTO.setAir_quality_status(determineAirQuality(airQualityNode.get("us-epa-index").asInt()));

            }

            // Set time of day based on "is_day" flag
            weatherDTO.setTime_of_day(currentNode.get("is_day").asInt() == 1 ? "pleasant day" : "pleasant evening");
            weatherDTO.setActivity_suggestion(currentNode.get("is_day").asInt() == 1 ? "outdoors" : "indoors");
        }

        weatherResponseDto.setWeather(weatherDTO);
        return weatherResponseDto;
    }

    private String determineAirQuality(int epaIndex) {
        switch (epaIndex) {
            case 1:
                return "good";
            case 2:
                return "moderate";
            case 3:
                return "unhealthy for sensitive groups";
            case 4:
                return "unhealthy";
            case 5:
                return "very unhealthy";
            case 6:
                return "hazardous";
            default:
                return "unknown";
        }
    }
}
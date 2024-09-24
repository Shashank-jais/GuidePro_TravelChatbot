package com.example.Chatbot.GuidePro_Backend.controller;

import com.example.Chatbot.GuidePro_Backend.model.Restaurant;
import com.example.Chatbot.GuidePro_Backend.model.RestaurantDto;
import com.example.Chatbot.GuidePro_Backend.service.RestaurantsLoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Ensure this import is present
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Home { // Renamed to RestaurantController for clarity

    @Autowired // Automatically injects the RestaurantsLoc service
    private RestaurantsLoc restaurantsLoc;

    @RequestMapping("/") // Home endpoint
    public String hello() {
        return "Hello World Mother"; // Consider changing the message to be more informative
    }






    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getRestaurants(@RequestParam String locationId) {
        try {
            Restaurant restaurantData = restaurantsLoc.fetchRestaurantsByLocationId(locationId);
            List<RestaurantDto> restaurants = restaurantData.getRestaurants();

            if (restaurants.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content if no restaurants found
            }

            return ResponseEntity.ok(restaurants); // 200 OK with the restaurant data
        } catch (NullPointerException e) {
            // Handle NullPointerException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            // Handle other exceptions and log the error
            System.err.println("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

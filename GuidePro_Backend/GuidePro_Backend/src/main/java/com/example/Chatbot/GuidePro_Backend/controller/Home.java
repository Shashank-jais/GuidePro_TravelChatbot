package com.example.Chatbot.GuidePro_Backend.controller;

import com.example.Chatbot.GuidePro_Backend.model.*;
import com.example.Chatbot.GuidePro_Backend.service.HotelsLoc;
import com.example.Chatbot.GuidePro_Backend.service.RestaurantsLoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class Home {

    @Autowired
    private RestaurantsLoc restaurantsLoc;

    @Autowired
    private HotelsLoc hotelsLoc;

    @Autowired
    private HomeDTO greet;

    @RequestMapping("/")
    public String hello() {
        return "Namaste Sir";
    }







    @GetMapping("/response")
    public ResponseEntity<?> NLP(@RequestParam String message) {
        String intent = "Home";
        switch (intent) {
            case "Home":
                greet.setMessage("Good Morning");
                greet.setIntent("normal");
                return ResponseEntity.ok(greet);

            case "Restaurantlist":
                // Hardcoded locationId for this example
                String locationId = message;

                try {
                    Restaurant restaurantData = restaurantsLoc.fetchRestaurantsByLocationId(locationId);
                    List<RestaurantDto> restaurants = restaurantData.getRestaurants();

                    if (restaurants.isEmpty()) {
                        return ResponseEntity.noContent().build(); // 204 No Content if no restaurants found
                    }

                    // Set the intent in the restaurantData object
                    restaurantData.setIntent(intent);

                    // Return the entire Restaurant object (with both the list of restaurants and the intent)
                    return ResponseEntity.ok(restaurantData); // 200 OK with the restaurant data and intent
                } catch (NullPointerException e) {
                    // Handle NullPointerException
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null Pointer Exception occurred");
                } catch (Exception e) {
                    // Handle other exceptions and log the error
                    System.err.println("An error occurred: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching restaurants");
                }


            case "Hotellist":
                // Using the message parameter as geoId
                String geoId = message ;  // Use the user's message as the geoId dynamically

                try {
                    Hotel hotelData = hotelsLoc.fetchHotelByGeoId(geoId);
                    List<HotelDTO> hotels = hotelData.getHotels();

                    if (hotels.isEmpty()) {
                        return ResponseEntity.noContent().build(); // 204 No Content if no hotels found
                    }

                    // Set the intent in the hotelData object
                    hotelData.setIntent(intent);

                    // Return the entire Hotel object (with both the list of hotels and the intent)
                    return ResponseEntity.ok(hotelData); // 200 OK with the hotel data and intent
                } catch (NullPointerException e) {
                    // Handle NullPointerException
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null Pointer Exception occurred");
                } catch (Exception e) {
                    // Handle other exceptions and log the error
                    System.err.println("An error occurred: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching hotels");
                }



            default:
                // Handle unknown intent
                return ResponseEntity.badRequest().body("Unknown intent"); // 400 Bad Request for unrecognized intent
        }

    }








//    @GetMapping("/restaurants")
//    public ResponseEntity<List<RestaurantDto>> getRestaurants(@RequestParam String locationId) {
//        try {
//            Restaurant restaurantData = restaurantsLoc.fetchRestaurantsByLocationId(locationId);
//            List<RestaurantDto> restaurants = restaurantData.getRestaurants();
//
//            if (restaurants.isEmpty()) {
//                return ResponseEntity.noContent().build(); // 204 No Content if no restaurants found
//            }
//
//            return ResponseEntity.ok(restaurants); // 200 OK with the restaurant data
//        } catch (NullPointerException e) {
//            // Handle NullPointerException
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        } catch (Exception e) {
//            // Handle other exceptions and log the error
//            System.err.println("An error occurred: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}

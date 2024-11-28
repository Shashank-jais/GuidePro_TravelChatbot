package com.example.Chatbot.GuidePro_Backend.controller;

import com.example.Chatbot.GuidePro_Backend.NLP.Main;
import com.example.Chatbot.GuidePro_Backend.NLP.NLPResult;
import com.example.Chatbot.GuidePro_Backend.model.*;
import com.example.Chatbot.GuidePro_Backend.service.*;
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
    private TouristPlacesLoc touristPlacesLoc;

    @Autowired
    private PlacesDetailLoc placesDetailLoc;

    @Autowired
    private HomeDTO greet;

    @Autowired
    private WeatherLoc weatherLoc;

    @Autowired
    private LocationIDLoc locationIDLoc;

    @Autowired
    private PlaceID_PlaceDetails placeIDPlaceDetails;


    @RequestMapping("/")
    public String hello() {
        return "Good Morning Sir";
    }


    @GetMapping("/response")
    public ResponseEntity<?> NLP(@RequestParam String message) {

        //NLP model will be implemented here which will be telling the important information like intent of query of user

        Main main = new Main();
        NLPResult obj = main.processNLP(message);
        String intent = obj.getClassifiedIntent();
        String Location = obj.getLocations().getFirst();
        System.out.println("--------------------------------------------------------");
        System.out.println("Intent: " + intent + " , Location : " + Location);
        System.out.println("--------------------------------------------------------");
        switch (intent) {
            case "Home":
                greet.setMessage("Good Morning");
                greet.setIntent("normal");
                return ResponseEntity.ok(obj);

            case "PlaceDetail":
                String place_id = placeIDPlaceDetails.fetchPlaceId(Location);
                try {
                    PlacesDetails details = placesDetailLoc.fetchPlaceDetails(place_id);
                    details.setIntent(intent);
                    return ResponseEntity.ok(details);

                } catch (NullPointerException e) {
                    // Handle NullPointerException
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null Pointer Exception occurred");
                } catch (Exception e) {
                    // Handle other exceptions and log the error
                    System.err.println("An error occurred: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching Place Details");
                }

            case "TouristPlaces":
                String place = Location;
                try {
                    TouristPlaces places = touristPlacesLoc.findByText(place);
                    List<TouristPlaceDTO> placeDTO = places.getPlaces();

                    if (placeDTO.isEmpty()) {
                        return ResponseEntity.noContent().build(); // 204 No Content if no restaurants found
                    }

                    // Set the intent in the restaurantData object
                    places.setIntent(intent);

                    // Return the entire Restaurant object (with both the list of restaurants and the intent)
                    return ResponseEntity.ok(places); // 200 OK with the restaurant data and intent
                } catch (NullPointerException e) {
                    // Handle NullPointerException
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null Pointer Exception occurred");
                } catch (Exception e) {
                    // Handle other exceptions and log the error
                    System.err.println("An error occurred: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching Tourist Attraction");
                }

            case "Restaurantlist":
                // Hardcoded locationId for this example
                String locationId = locationIDLoc.fetchLocationIDByQuery(Location);

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
                String geoId = locationIDLoc.fetchLocationIDByQuery(Location);  // Use the user's message as the geoId dynamically

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


            case "Weather":
                // Hardcoded location name (city) for this example. Replace "message" with the input query/location
                String city = Location;

                try {
                    // Fetch weather data using the WeatherLoc service
                    Weather weather = weatherLoc.fetchWeatherByCity(city);
                    WeatherDTO weatherData = weather.getWeather();

                    if (weatherData == null) {
                        // If no weather data is found, return a 204 No Content response
                        return ResponseEntity.noContent().build();
                    }

                    // Set the intent if required, for tracking purposes
                    weather.setIntent(intent); // If you need intent in the weather response

                    // Return the entire WeatherDTO object (with all the weather details)
                    return ResponseEntity.ok(weather); // 200 OK with weather data
                } catch (NullPointerException e) {
                    // Handle NullPointerException and log the error
                    System.err.println("NullPointerException occurred: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Null Pointer Exception occurred");
                } catch (Exception e) {
                    // Handle other exceptions and log the error
                    System.err.println("An error occurred while fetching weather: " + e.getMessage());
                    String errorMessage = "Could not fetch weather data for " + city + ". Please check the city name and try again.";
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching weather data");
//                    return ResponseEntity.ok(errorMessage);
                }


            default:
                // Handle unknown intent
                greet.setMessage("Not able to understand the question");
                greet.setIntent("normal");
                return ResponseEntity.ok(greet);
//                return ResponseEntity.badRequest().body("Unknown intent"); // 400 Bad Request for unrecognized intent
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

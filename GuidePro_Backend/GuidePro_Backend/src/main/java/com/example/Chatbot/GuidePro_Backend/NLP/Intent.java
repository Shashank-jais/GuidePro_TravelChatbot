package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.Arrays;
import java.util.List;

public class Intent {

    // Define the intent types
    public enum IntentType {
        PlaceDetail,
        TouristPlaces,
        GET_BEST_TIME_TO_VISIT,
        Hotellist,
        Restaurantlist,
        PLAN_ITINERARY,
        GET_TRAVEL_REQUIREMENTS,
        Weather,
        GET_EMERGENCY_INFO,
        Basics,
        UNKNOWN
    }

    public IntentType classifyIntent(String lemmatizedInput) {
        // Define keywords for each intent
        List<String> getDestinationInfoKeywords = Arrays.asList(
                "info", "information", "explain",
                "feature", "insight", "summary", "characteristics" , "detail"
        );
        List<String> getTopAttractionsKeywords = Arrays.asList(
                "must see", "famous", "attractions","must-see", "can i do","can i see", "i can see", "thing", "sights", "visit", "recommendation"
        );
        List<String> getBestTimeToVisitKeywords = Arrays.asList("best time", "when");
        List<String> getAccommodationKeywords = Arrays.asList(
                "hotels", "luxury hotels", "budget hotels", "lodging", "stay", "dharamshala",
                "sleep", "guest house", "accommodation"
        );
        List<String> getDiningKeywords = Arrays.asList(
                "vegan", "vegetarian", "restaurant", "meals", " eat ", "food", "bite", "snack",
                "lunch", "dinner", "breakfast", "brunch"
        );
        List<String> planItineraryKeywords = Arrays.asList(
                "itinerary", "plan", "suggestion", "guide"
        );
        List<String> getWeatherInfoKeywords = Arrays.asList(
                "weather", "raining", "sunny", "rainy", "hot", "chilly", "cold", "windy",
                "climate", "forecast", "temperature"
        );
        List<String> getEmergencyInfoKeywords = Arrays.asList(
                "emergency", "hospital", "police", "police station"
        );
        List<String> getBasicsInfoKeywords = Arrays.asList(
                "mall", "general store", "general-store", "gas station", "petrol pump",
                "mechanic", "pharmacy", "medical shop", "ATM", "restroom"
        );

        // Convert input to lowercase for case-insensitive comparison
        String input = lemmatizedInput.toLowerCase();

        // Classify intent based on keywords
        if (containsKeyword(input, getDestinationInfoKeywords)) {
            return IntentType.PlaceDetail;
        } else if (containsKeyword(input, getTopAttractionsKeywords)) {
            return IntentType.TouristPlaces;
        } else if (containsKeyword(input, getBestTimeToVisitKeywords)) {
            return IntentType.GET_BEST_TIME_TO_VISIT;
        } else if (containsKeyword(input, getDiningKeywords)) {
            return IntentType.Restaurantlist;
        } else if (containsKeyword(input, getAccommodationKeywords)) {
            return IntentType.Hotellist;
        } else if (containsKeyword(input, planItineraryKeywords)) {
            return IntentType.PLAN_ITINERARY;
        } else if (containsKeyword(input, getWeatherInfoKeywords)) {
            return IntentType.Weather;
        } else if (containsKeyword(input, getEmergencyInfoKeywords)) {
            return IntentType.GET_EMERGENCY_INFO;
        } else if (containsKeyword(input, getBasicsInfoKeywords)) {
            return IntentType.Basics;
        } else {
            return IntentType.UNKNOWN; // If no matching intent is found
        }
    }

    // Helper method to check if input contains any of the keywords
    private boolean containsKeyword(String input, List<String> keywords) {
        return keywords.stream().anyMatch(input::contains);
    }
}

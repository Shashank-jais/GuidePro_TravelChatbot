//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Intent {
//
//    // Define the intent types
//    public enum IntentType {
//        GET_DESTINATION_INFO,
//        GET_TOP_ATTRACTIONS,
//        GET_BEST_TIME_TO_VISIT,
//        GET_LOCAL_TRANSPORTATION,
//        GET_ACCOMMODATION_DINING,
//        PLAN_ITINERARY,
//        GET_CURRENCY_LANGUAGE_INFO,
//        GET_TRAVEL_REQUIREMENTS,
//        GET_WEATHER_INFO,
//        GET_EMERGENCY_INFO,
//        UNKNOWN
//    }
//
//    // Method to classify intent based on lemmatized input
//    public IntentType classifyIntent(String lemmatizedInput) {
//
//        // Define keywords for each intent
//        List<String> getDestinationInfoKeywords = Arrays.asList("about", "famous", "attractions");
//        List<String> getTopAttractionsKeywords = Arrays.asList("must see", "must-see", "can i do");
//        List<String> getBestTimeToVisitKeywords = Arrays.asList("best time");
//        List<String> getLocalTransportationKeywords = Arrays.asList("transportation", "i get around");
//        List<String> getAccommodationDiningKeywords = Arrays.asList("hotels", "vegan", "vegetarian", "restaurants", "luxury hotels", "budget hotels");
//        List<String> planItineraryKeywords = Arrays.asList("itinerary", "plan", "suggestion", "guide");
//        List<String> getCurrencyLanguageInfoKeywords = Arrays.asList("currency", "money");
//        List<String> getWeatherInfoKeywords = Arrays.asList("weather", "raining", "sunny", "rainy", "hot", "chilly", "cold");
//        List<String> getEmergencyInfoKeywords = Arrays.asList("emergency", "hospital", "police", "police station");
//
//        // Convert input to lowercase for case-insensitive comparison
//        String input = lemmatizedInput.toLowerCase();
//
//        // Classify intent based on keywords
//        if (containsKeyword(input, getDestinationInfoKeywords)) {
//            return IntentType.GET_DESTINATION_INFO;
//        } else if (containsKeyword(input, getTopAttractionsKeywords)) {
//            return IntentType.GET_TOP_ATTRACTIONS;
//        } else if (containsKeyword(input, getBestTimeToVisitKeywords)) {
//            return IntentType.GET_BEST_TIME_TO_VISIT;
//        } else if (containsKeyword(input, getLocalTransportationKeywords)) {
//            return IntentType.GET_LOCAL_TRANSPORTATION;
//        } else if (containsKeyword(input, getAccommodationDiningKeywords)) {
//            return IntentType.GET_ACCOMMODATION_DINING;
//        } else if (containsKeyword(input, planItineraryKeywords)) {
//            return IntentType.PLAN_ITINERARY;
//        } else if (containsKeyword(input, getCurrencyLanguageInfoKeywords)) {
//            return IntentType.GET_CURRENCY_LANGUAGE_INFO;
//        } else if (containsKeyword(input, getWeatherInfoKeywords)) {
//            return IntentType.GET_WEATHER_INFO;
//        } else if (containsKeyword(input, getEmergencyInfoKeywords)) {
//            return IntentType.GET_EMERGENCY_INFO;
//        } else {
//            return IntentType.UNKNOWN; // If no matching intent is found
//        }
//    }
//
//    // Helper method to check if input contains any of the keywords
//    private boolean containsKeyword(String input, List<String> keywords) {
//        for (String keyword : keywords) {
//            if (input.contains(keyword)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}

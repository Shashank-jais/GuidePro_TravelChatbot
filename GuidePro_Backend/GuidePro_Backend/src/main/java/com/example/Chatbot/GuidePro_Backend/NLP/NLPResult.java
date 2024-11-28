package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.List;

public class NLPResult {
    private List<String> locations;
    private Intent.IntentType classifiedIntent;

    // Constructor
    public NLPResult(List<String> locations, Intent.IntentType classifiedIntent) {
        this.locations = locations;
        this.classifiedIntent = classifiedIntent;
    }

    // Getters and setters
    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getClassifiedIntent() {
        return classifiedIntent.toString();
    }

    public void setClassifiedIntent(Intent.IntentType classifiedIntent) {
        this.classifiedIntent = classifiedIntent;
    }

//    @Override
//    public String toString() {
//        return "NLPResult{" +
//                "locations=" + locations +
//                ", classifiedIntent=" + classifiedIntent +
//                '}';
//    }
}


package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;

import java.util.List;

@Data
public class TouristPlaces {
    private String intent;
    private List<TouristPlaceDTO> places;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<TouristPlaceDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<TouristPlaceDTO> places) {
        this.places = places;
    }
}

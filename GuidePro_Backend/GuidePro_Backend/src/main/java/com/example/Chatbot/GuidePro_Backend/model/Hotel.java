package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Hotel {
    private String intent;
    private List<HotelDTO> hotels;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<HotelDTO> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelDTO> hotels) {
        this.hotels = hotels;
    }
}

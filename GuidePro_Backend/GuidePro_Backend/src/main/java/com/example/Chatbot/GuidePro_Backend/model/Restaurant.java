package com.example.Chatbot.GuidePro_Backend.model;


import lombok.Data;

import java.util.List;

@Data
public class Restaurant {
    private List<RestaurantDto> restaurants;

    public List<RestaurantDto> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDto> restaurants) {
        this.restaurants = restaurants;
    }
}


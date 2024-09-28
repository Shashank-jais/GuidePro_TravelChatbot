package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class RestaurantDetailsDTO {

    private String location_id;
    private String name;
    private String description;
    private String web_url;
    private String address;
    private List<String> cuisine;
    private String currentHours;

    public RestaurantDetailsDTO() {
    }

    public RestaurantDetailsDTO(String location_id, String name, String description, String web_url, String address, List<String> cuisine, String currentHours) {
        this.location_id = location_id;
        this.name = name;
        this.description = description;
        this.web_url = web_url;
        this.address = address;
        this.cuisine = cuisine;
        this.currentHours = currentHours;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<String> cuisine) {
        this.cuisine = cuisine;
    }

    public String getCurrentHours() {
        return currentHours;
    }

    public void setCurrentHours(String currentHours) {
        this.currentHours = currentHours;
    }
}

//location_id
//name:
//description
//        web_url
//address
//cuisine : [
//        0: {
//key:"10346"
//name:"Indian" } ]
//currentHoursText

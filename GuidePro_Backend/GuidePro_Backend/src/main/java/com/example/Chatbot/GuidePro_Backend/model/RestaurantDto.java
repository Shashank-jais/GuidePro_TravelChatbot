package com.example.Chatbot.GuidePro_Backend.model;


import lombok.Data;

import java.util.List;

@Data
public class RestaurantDto {
    private String name;
    private int averageRating;
    private List<String> establishmentTypeAndCuisineTags;
    private String heroImgUrl;
    private int heroImgRawHeight;
    private int heroImgRawWidth;


    public RestaurantDto() {
    }

    public RestaurantDto(String name, int averageRating, List<String> establishmentTypeAndCuisineTags,
                         String heroImgUrl, int heroImgRawHeight, int heroImgRawWidth) {
        this.name = name;
        this.averageRating = averageRating;
        this.establishmentTypeAndCuisineTags = establishmentTypeAndCuisineTags;
        this.heroImgUrl = heroImgUrl;
        this.heroImgRawHeight = heroImgRawHeight;
        this.heroImgRawWidth = heroImgRawWidth;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getEstablishmentTypeAndCuisineTags() {
        return establishmentTypeAndCuisineTags;
    }

    public void setEstablishmentTypeAndCuisineTags(List<String> establishmentTypeAndCuisineTags) {
        this.establishmentTypeAndCuisineTags = establishmentTypeAndCuisineTags;
    }

    public String getHeroImgUrl() {
        return heroImgUrl;
    }

    public void setHeroImgUrl(String heroImgUrl) {
        this.heroImgUrl = heroImgUrl;
    }

    public int getHeroImgRawWidth() {
        return heroImgRawWidth;
    }

    public void setHeroImgRawWidth(int heroImgRawWidth) {
        this.heroImgRawWidth = heroImgRawWidth;
    }

    public int getHeroImgRawHeight() {
        return heroImgRawHeight;
    }

    public void setHeroImgRawHeight(int heroImgRawHeight) {
        this.heroImgRawHeight = heroImgRawHeight;
    }


// Getters and Setters
}

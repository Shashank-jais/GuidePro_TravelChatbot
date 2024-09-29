package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class HotelDTO {
    private String id;
    private String title;
    private String primaryInfo;
    private Double rating;
    private String priceForDisplay;
    private String priceDetails;
    private String externalUrl;

    public HotelDTO() {
    }

    public HotelDTO(String id, String title, String primaryInfo, Double rating, String priceForDisplay, String priceDetails,String externalUrl) {
        this.id = id;
        this.title = title;
        this.primaryInfo = primaryInfo;
        this.rating = rating;
        this.priceForDisplay = priceForDisplay;
        this.priceDetails = priceDetails;
        this.externalUrl = externalUrl;
    }
}

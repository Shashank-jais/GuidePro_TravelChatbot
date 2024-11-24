package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class TouristPlaceDTO {
    private String _id;
    private String City;
    private String Place;
    private String Ratings;
    private String Place_desc;

    public TouristPlaceDTO(String _id, String city, String place, String ratings, String place_desc) {
        this._id = _id;
        City = city;
        Place = place;
        Ratings = ratings;
        Place_desc = place_desc;
    }

    public TouristPlaceDTO() {
    }
}

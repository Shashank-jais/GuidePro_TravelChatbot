package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlacesDetailDTO {
    private String add;
    private double lat;
    private double lon;
    private String link;
    private String place_id;

}

package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;


@Data

public class PlacesDetails {
    private String intent;
    private PlacesDetailDTO details;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public PlacesDetailDTO getDetails() {
        return details;
    }

    public void setDetails(PlacesDetailDTO details) {
        this.details = details;
    }
}

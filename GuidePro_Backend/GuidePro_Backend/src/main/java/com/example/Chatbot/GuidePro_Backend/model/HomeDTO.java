package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class HomeDTO {
    private String intent;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}

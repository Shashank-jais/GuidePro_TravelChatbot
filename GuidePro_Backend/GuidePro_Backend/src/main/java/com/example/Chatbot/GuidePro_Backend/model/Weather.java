package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Weather {
    private String intent;
    private WeatherDTO weather;
}

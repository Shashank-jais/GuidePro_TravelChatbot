package com.example.Chatbot.GuidePro_Backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WeatherDTO {
    private String location_name;
    private String region;
    private String country;
    private String condition_text;
    private double temp_c;
    private double feelslike_c;
    private int humidity;
    private String wind_dir;
    private double wind_kph;
    private String air_quality_status;
    private double pm2_5;
    private int usEpaIndex;
    private double vis_km;
    private double precip_mm;
    private int uv;
    private String time_of_day;
    private String activity_suggestion;

    public WeatherDTO(){

    }

    public WeatherDTO(String location_name, String region, String country, String condition_text, double temp_c, double feelslike_c, int humidity, String wind_dir, double wind_kph, String air_quality_status, double pm2_5, int usEpaIndex ,double vis_km, double precip_mm, int uv, String time_of_day, String activity_suggestion) {
        this.location_name = location_name;
        this.region = region;
        this.country = country;
        this.condition_text = condition_text;
        this.temp_c = temp_c;
        this.feelslike_c = feelslike_c;
        this.humidity = humidity;
        this.wind_dir = wind_dir;
        this.wind_kph = wind_kph;
        this.air_quality_status = air_quality_status;
        this.pm2_5 = pm2_5;
        this.usEpaIndex=usEpaIndex;
        this.vis_km = vis_km;
        this.precip_mm = precip_mm;
        this.uv = uv;
        this.time_of_day = time_of_day;
        this.activity_suggestion = activity_suggestion;
    }
}

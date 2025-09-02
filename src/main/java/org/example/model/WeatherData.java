package org.example.model;

import java.time.LocalDate;

public record WeatherData(
        String city,
        LocalDate date,
        double minTemp,
        double maxTemp,
        double humidity,
        double windSpeed,
        String windDir
) {
}

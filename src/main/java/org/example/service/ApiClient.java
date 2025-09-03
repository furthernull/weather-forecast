package org.example.service;

import java.util.List;
import org.example.model.WeatherData;

public interface ApiClient {
    List<WeatherData> fetchWeatherData(List<String> cities);

    WeatherData getForecast(String city);
}

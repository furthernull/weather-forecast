package org.example.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import org.example.exception.WeatherApiException;
import org.example.model.WeatherData;
import org.example.service.ApiClient;

public class WeatherApiClient implements ApiClient {
    private static final String BASE_URL = "http://api.weatherapi.com/v1";
    private final String apiKey;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public WeatherApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<WeatherData> fetchWeatherData(List<String> cities) {
        return cities
                .stream()
                .map(this::getForecast)
                .toList();
    }

    @Override
    public WeatherData getForecast(String city) {
        String url = String.format("%s/forecast.json?q=%s&days=1&dt=%s&key=%s"
                , BASE_URL, city, getTomorrowDate(), apiKey);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            JsonNode rootWeather = mapper.readTree(response.body());
            JsonNode forecastDay = rootWeather.path("forecast")
                    .path("forecastday").get(0).get("day");

            return new WeatherData(
                    rootWeather.path("location").get("name").asText(),
                    LocalDate.parse(rootWeather.path("forecast")
                                    .path("forecastday").get(0).get("date").asText()),
                    forecastDay.get("mintemp_c").asDouble(),
                    forecastDay.get("maxtemp_c").asDouble(),
                    forecastDay.get("avghumidity").asDouble(),
                    forecastDay.get("maxwind_kph").asDouble(),
                    rootWeather.path("forecast").path("forecastday")
                            .get(0).get("hour").get(0).path("wind_dir").asText()
            );
        } catch (IOException | InterruptedException e) {
            throw new WeatherApiException("Fetching error: " + e.getMessage());
        }
    }

    private LocalDate getTomorrowDate() {
        return LocalDate.now().plusDays(1);
    }
}

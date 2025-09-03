package org.example.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.example.dto.ForecastDay;
import org.example.dto.WeatherApiResponse;
import org.example.exception.WeatherApiException;
import org.example.model.WeatherData;
import org.example.service.ApiClient;
import org.example.service.WeatherApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitWeatherApiClient implements ApiClient {
    private static final String BASE_URL = "http://api.weatherapi.com/v1/";
    private final String apiKey;
    private final WeatherApiService apiService;

    public RetrofitWeatherApiClient(String apiKey) {
        this.apiKey = apiKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(WeatherApiService.class);
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
        Call<WeatherApiResponse> forecast =
                apiService.getForecast(apiKey, city, 1, getTomorrowDate());

        try {
            Response<WeatherApiResponse> response = forecast.execute();
            if (!response.isSuccessful()) {
                throw new WeatherApiException("Can't fetch data for city " + city);
            }

            WeatherApiResponse body = response.body();
            ForecastDay forecastDay = body.forecast().forecastDays().get(0);

            return new WeatherData(
                    body.location().name(),
                    LocalDate.parse(forecastDay.date()),
                    forecastDay.day().minTempC(),
                    forecastDay.day().maxTempC(),
                    forecastDay.day().avgHumidity(),
                    forecastDay.day().maxWindKph(),
                    forecastDay.hours().get(0).windDir()
            );

        } catch (IOException e) {
            throw new WeatherApiException("Fetching error: " + e.getMessage());
        }
    }

    private String getTomorrowDate() {
        return LocalDate.now().plusDays(1).toString();
    }
}

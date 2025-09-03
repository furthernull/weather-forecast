package org.example.service;

import org.example.dto.WeatherApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("forecast.json")
    Call<WeatherApiResponse> getForecast(
            @Query("key") String apiKey,
            @Query("q") String city,
            @Query("days") int days,
            @Query("dt") String date
    );
}

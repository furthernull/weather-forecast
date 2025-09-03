package org.example;


import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;
import org.example.model.WeatherData;
import org.example.service.ApiClient;
import org.example.service.Printer;
import org.example.service.impl.RetrofitWeatherApiClient;
import org.example.service.impl.WeatherApiClient;
import org.example.service.impl.WeatherTablePrinter;

public class WeatherApp {
    public static void main(String[] args) {
        String apiKey = Dotenv.load().get("WEATHER_API_KEY");

//        ApiClient apiClient = new WeatherApiClient(apiKey);
        ApiClient apiClient = new RetrofitWeatherApiClient(apiKey);

        Printer<WeatherData> printer = new WeatherTablePrinter();

        List<String> cities = List.of("Chisinau", "Madrid", "Kyiv", "Amsterdam");
        List<WeatherData> forecast = apiClient.fetchWeatherData(cities);

        printer.print(forecast);
    }
}

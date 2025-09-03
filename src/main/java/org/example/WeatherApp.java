package org.example;


import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;
import org.example.model.WeatherData;
import org.example.service.ApiClient;
import org.example.service.Printer;
import org.example.service.impl.RetrofitWeatherApiClient;
import org.example.service.impl.WeatherTablePrinter;

public class WeatherApp {
    public static final String CHISINAU_CITY = "Chisinau";
    public static final String MADRID_CITY = "Madrid";
    public static final String KYIV_CITY = "Chisinau";
    public static final String AMSTERDAM_CITY = "Chisinau";

    public static void main(String[] args) {
        String apiKey = Dotenv.load().get("WEATHER_API_KEY");

        ApiClient apiClient = new RetrofitWeatherApiClient(apiKey);

        Printer<WeatherData> printer = new WeatherTablePrinter();

        List<String> cities = List.of(CHISINAU_CITY, MADRID_CITY, KYIV_CITY, AMSTERDAM_CITY);
        List<WeatherData> forecast = apiClient.fetchWeatherData(cities);


        printer.print(forecast);
    }
}

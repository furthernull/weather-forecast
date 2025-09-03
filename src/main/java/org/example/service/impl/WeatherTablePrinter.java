package org.example.service.impl;

import java.util.List;
import org.example.model.WeatherData;
import org.example.service.Printer;

public class WeatherTablePrinter implements Printer<WeatherData> {
    private static final String DATE = "Date";
    private static final String CITY = "City";
    private static final String HEADER_ROW_TEMPLATE =
            "%-15s | %-70s";
    private static final String WEATHER_DATA_TEMPLATE =
            "%-15s | Min Temp: %.1f°C, Max Temp: %.1f°C, Humidity: %.0f%%, Wind: %.1fkph %s";
    private static final String SEPARATOR = "-".repeat(85);

    @Override
    public void print(List<WeatherData> data) {
        String date = data
                .stream()
                .map(wd -> wd.date().toString())
                .distinct()
                .sorted()
                .findFirst()
                .orElse(DATE);

        printHeader(date);

        data.forEach(wd -> {
            System.out.println(String.format(WEATHER_DATA_TEMPLATE, wd.city(), wd.minTemp(),
                    wd.maxTemp(), wd.humidity(), wd.windSpeed(), wd.windDir()));
        });
    }

    @Override
    public void print(WeatherData data) {
        print(List.of(data));
    }

    private void printHeader(String date) {
        System.out.println(String.format(HEADER_ROW_TEMPLATE,
                CITY, date));
        System.out.println(SEPARATOR);
    }
}

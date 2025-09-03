package org.example.exception;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException() {
    }

    public WeatherApiException(String message) {
        super(message);
    }
}

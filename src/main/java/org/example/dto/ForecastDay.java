package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastDay(
        String date,
        Day day,
        @JsonProperty("hour") List<Hour> hours
) {
}

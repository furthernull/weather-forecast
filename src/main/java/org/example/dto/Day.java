package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Day(
        @JsonProperty("maxtemp_c") double maxTempC,
        @JsonProperty("mintemp_c") double minTempC,
        @JsonProperty("avghumidity") double avgHumidity,
        @JsonProperty("maxwind_kph") double maxWindKph
) {
}

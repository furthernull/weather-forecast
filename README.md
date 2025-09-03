# Weather forecast

A simple Java console application that fetches the next day's weather forecast for a list of cities using [WeatherAPI.com](https://www.weatherapi.com/).  
The project demonstrates integration with **Retrofit** and prints the forecast in a formatted table.

## Features
- Fetches forecast for:
    - Chisinau
    - Madrid
    - Kyiv
    - Amsterdam
- Outputs:
    - Minimum Temperature (°C)
    - Maximum Temperature (°C)
    - Humidity (%)
    - Wind Speed (kph)
    - Wind Direction
- Two implementations of API client:
    - `WeatherApiClient` – using Java `HttpClient`
    - `RetrofitWeatherApiClient` – using **Retrofit**

## Requirements
- Java 17+
- Maven
- WeatherAPI.com API key (free tier works)

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/furthernull/weather-forecast.git
   cd weather-forecast
2. Create .env file in the project root with your API key:
   ```
   WEATHER_API_KEY=your_api_key_here
3. Build the project:
   ```bash
   mvn clean install
4. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.WeatherApp"

### Example Output
   ```
   City            | 2025-09-04
   -------------------------------------------------------------------------------------
   Chisinau        | Min Temp: 18.2°C, Max Temp: 27.3°C, Humidity: 62%, Wind: 13.5kph NW
   Madrid          | Min Temp: 21.1°C, Max Temp: 32.7°C, Humidity: 48%, Wind: 19.2kph NE
   Kyiv            | Min Temp: 16.4°C, Max Temp: 25.8°C, Humidity: 70%, Wind: 15.0kph W
   Amsterdam       | Min Temp: 14.7°C, Max Temp: 22.3°C, Humidity: 75%, Wind: 12.8kph SW
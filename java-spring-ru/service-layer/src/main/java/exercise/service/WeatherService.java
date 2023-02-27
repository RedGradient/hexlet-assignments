package exercise.service;

import exercise.HttpClient;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    private static final String WEATHER_BASE_URL = "http://weather/api/v2/cities/";

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(String city) {
        var response = client.get(WEATHER_BASE_URL + city);
        var mapper = new ObjectMapper();

        Map<String, String> result;
        try {
            result = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return result;
    }
    // END
}

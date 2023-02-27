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
    public LinkedHashMap<String, Object> getWeather(String city) throws ParseException {
        var weather = client.get(WEATHER_BASE_URL + city);
        var jsonParser = new JSONParser(weather);
        return jsonParser.parseObject();
    }
    // END
}

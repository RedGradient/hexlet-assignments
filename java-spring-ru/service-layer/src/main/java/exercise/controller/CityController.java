package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCityData(@PathVariable Long id) throws ParseException {
        var city = cityRepository.findById(id).orElseThrow(
            () -> new CityNotFoundException("City not found")
        );
        return weatherService.getWeather(city.getName());
    }

    @GetMapping(path = "/search")
    public Iterable<Map<String, String>> getCitiesTemperature(@RequestParam(defaultValue = "") String name) {

        Iterable<City> cities;
        if (name.isEmpty()) {
            cities = cityRepository.findAllByOrderByName();
        } else {
            cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        var temperatures = new ArrayList<Map<String, String>>();
        for (var city : cities) {
            var weather = weatherService.getWeather(city.getName());
            temperatures.add(Map.of(
                    "temperature", weather.get("temperature"),
                    "name", city.getName()
            ));
        }
        return temperatures;
    }
    // END
}

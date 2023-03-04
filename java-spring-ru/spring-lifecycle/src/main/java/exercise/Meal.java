package exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// BEGIN
@Component("meal")
// END
public class Meal {
    public String getMealForDaytime(String daytime) {
        return switch (daytime) {
            case "morning" -> "breakfast";
            case "day" -> "lunch";
            case "evening" -> "dinner";
            default -> "nothing :)";
        };
    }

    // Для самостоятельной работы
    // BEGIN
    @PostConstruct
    void init() {
        System.out.println("Init bean meal");
    }
    // END
}

package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.util.comparator.Comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
// Указываем общую информацию о нашем API
@OpenAPIDefinition(
    info = @Info(
        // Название
        title = "Users API",
        // Версия
        version = "1.0",
        // Описание
        description = "Information about users"
    )
)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // Запускаем приложение
//        var app = new SpringApplication(App.class);
//        app.setDefaultProperties(Collections.singletonMap("server.port", "5001"));
         SpringApplication.run(App.class, args);
//        app.run();
    }
}

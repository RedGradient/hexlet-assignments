package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path filePath, Car car) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(car);
        Files.write(filePath, json.getBytes());
    }

    public static Car extract(Path filePath) throws IOException {
        var json = Files.readString(filePath);
        return Car.unserialize(json);
    }
}
// END

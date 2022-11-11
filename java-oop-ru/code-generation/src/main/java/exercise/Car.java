package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car unserialize(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree = null;
        try {
            tree = objectMapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        var carJson = tree.get("owner").toString();

        Integer id = null;
        String brand = null;
        String model = null;
        String color = null;
        User owner = null;
        try {
            id = tree.get("id").asInt();
            brand = tree.get("brand").asText();
            model = tree.get("model").asText();
            color = tree.get("color").asText();
            owner = objectMapper.readValue(carJson, User.class);
            return new Car(id, brand, model, color, owner);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}

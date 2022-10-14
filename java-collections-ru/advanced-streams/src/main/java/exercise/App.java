package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {

        String[] splitted = content.split("\n");

        var variablesList = Arrays.stream(splitted)
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.replaceAll("environment=", ""))
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .filter(line -> line.contains("X_FORWARDED_"))
                .map(line -> line.replaceAll("X_FORWARDED_", ""))
                .peek(System.out::println)
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        return String.join(",", variablesList);
    }
}
//END

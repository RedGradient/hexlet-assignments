package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildAppartmentsList(List<Home> homes, int count) {
        return homes.stream().sorted(
                Comparator.comparingDouble(Home::getArea)
        ).map(Object::toString).limit(count).toList();
    }
}
// END

package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> people) {

        return people
                .stream()
                .filter(person -> person.get("gender").equals("male"))
                .sorted(Comparator.comparingLong(person -> LocalDate.parse(person.get("birthday")).toEpochDay()))
                .map(person -> person.get("name"))
                .toList();
    }
}
// END

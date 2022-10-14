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

    private static Date getBirthdayDate(Map<String, String> person) {
        var birthdayDateString = person.get("birthday");
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(birthdayDateString);
        } catch (ParseException ignored) {}
        return null;
    }

    public static List<String> takeOldestMans(List<Map<String, String>> people) {

        return people
                .stream()
                .filter(person -> person.get("gender").equals("male"))
                .sorted(Comparator.comparing(Sorter::getBirthdayDate))
                .map(person -> person.get("name"))
                .toList();
    }
}
// END

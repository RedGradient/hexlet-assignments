package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
class App {

    private static final List<String> FREE_DOMAINS = Arrays.asList("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails
                .stream()
                .map(email -> email.split("@")[1])
                .filter(FREE_DOMAINS::contains)
                .count();
    }
}
// END

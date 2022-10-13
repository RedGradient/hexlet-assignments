package exercise;

import java.util.List;

// BEGIN
class App {

    private static final String[] freeDomains = {"gmail.com", "yandex.ru", "hotmail.com"};

    private static boolean isDomainFree(String text) {
        for (String item : App.freeDomains) {
            if (text.endsWith(item)) {
                return true;
            }
        }
        return false;
    }

    public static int getCountOfFreeEmails(List<String> emails) {
        var count = emails.stream()
                .filter(App::isDomainFree)
                .count();
        return (int) count;
    }
}
// END

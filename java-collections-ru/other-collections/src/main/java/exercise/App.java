package exercise;

import java.util.*;

// BEGIN
class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> dict1, Map<String, Object> dict2) {

        Set<String> keys = new TreeSet<>();
        keys.addAll(dict1.keySet());
        keys.addAll(dict2.keySet());

        LinkedHashMap<String, String> difference = new LinkedHashMap<>();

        for (var key : keys) {
            if (dict1.containsKey(key) && dict2.containsKey(key)) {
                var dict1Value = dict1.get(key);
                var dict2Value = dict2.get(key);
                if (dict1Value.equals(dict2Value)) {
                    difference.put(key, "unchanged");
                } else {
                    difference.put(key, "changed");
                }
            } else if (!dict1.containsKey(key) && dict2.containsKey(key)) {
                difference.put(key, "added");
            } else if (dict1.containsKey(key) && !dict2.containsKey(key)) {
                difference.put(key, "deleted");
            }
        }

        return difference;

    }
}
//END

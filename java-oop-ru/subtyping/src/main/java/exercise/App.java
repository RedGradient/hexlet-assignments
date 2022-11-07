package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        for (var pair : storage.toMap().entrySet()) {
            storage.unset(pair.getKey());
            storage.set(pair.getValue(), pair.getKey());
        }
    }
}
// END

package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static HashMap<String, Integer> getWordCount(String text) {

        HashMap<String, Integer> result = new HashMap<>();

        if (text.isEmpty()) {
            return result;
        }

        var splitted = text.split(" ");
        var length = splitted.length;
        for (var i = 0; i < length; i++) {
            var currentWord = splitted[i];
            var currentWordCount = result.getOrDefault(currentWord, 0) + 1;
            if (!result.containsKey(currentWord)) {
                result.put(currentWord, currentWordCount);
            } else {
                result.replace(currentWord, currentWordCount);
            }
        }

        return result;
    }

    public static String toString(Map<String, Integer> someMap) {
        if (someMap.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, Integer> word : someMap.entrySet()) {
            result.append("  ");
            result.append(word.getKey());
            result.append(": ");
            result.append(word.getValue());
            result.append("\n");
        }

        result.append("}");

        return result.toString();
    }
}
//END

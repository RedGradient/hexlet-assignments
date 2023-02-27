package exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// BEGIN
public class App {
    private static HashMap<Character, Integer> getCharactersCountMap(String word) {
        HashMap<Character, Integer> lettersCountMap = new HashMap<>();

        for (var i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            var currentCharCount = lettersCountMap.getOrDefault(currentChar, 0) + 1;
            if (!lettersCountMap.containsKey(currentChar)) {
                lettersCountMap.put(currentChar, currentCharCount);
            } else {
                lettersCountMap.replace(currentChar, currentCharCount);
            }
        }

        return lettersCountMap;
    }
    public static boolean scrabble(String characters, String word) {

        if (characters.isEmpty()) {
            return false;
        }

        HashMap<Character, Integer> charactersCountMap = getCharactersCountMap(characters);
        HashMap<Character, Integer> wordCharactersCountMap = getCharactersCountMap(word.toLowerCase());

        for (Map.Entry<Character, Integer> pair : wordCharactersCountMap.entrySet()) {
            var currentChar = pair.getKey();
            if (charactersCountMap.get(currentChar) < pair.getValue()) {
                return false;
            }
        }

        return true;
    }
}
//END

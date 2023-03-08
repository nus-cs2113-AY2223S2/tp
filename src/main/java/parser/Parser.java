package parser;

import java.util.ArrayList;

public class Parser {

    private static final String WHITESPACE = " ";

    public String extractCommandKeyword (String userInput) {
        String[] input = userInput.split(WHITESPACE);
        return input[0].toLowerCase();
    }

    public String extractCommandParameters (String parameterType, String userInput) {
        int parameterTypeLength = parameterType.length();
        String[] words = userInput.split(WHITESPACE);
        ArrayList<String> results = new ArrayList<String>();
        for (String word : words) {
            if (word.startsWith(parameterType)) {
                results.add(word.substring(parameterTypeLength));
            }
        }
        return results.get(results.size()-1);
    }

}

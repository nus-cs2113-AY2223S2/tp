package parser;

import java.util.HashMap;

public class ParsedCommand {

    public static final int MAX_ACCEPTED_PARAMETERS = 100;
    protected String commandWord;

    protected HashMap<String, String> parsedParameters;

    public ParsedCommand(String input) {
        String[] words = input.split(Parser.WHITESPACE);
        for (int i = 1; i < words.length; i++) {
            // first of each parameter type is taken if duplicate occurs , reverse for loop to take last
            int indexOfBackslash = words[i].indexOf("/", 2);
            if (indexOfBackslash == -1 || indexOfBackslash == words[i].length()) {
                continue;
            }
            String parameterType = words[i].substring(0, indexOfBackslash);
            if (parsedParameters.containsKey(parameterType)) {
                continue;
            }
            String parameterValue = words[i].substring(indexOfBackslash + 1);
            parsedParameters.put(parameterType, parameterValue);
        }
    }
}

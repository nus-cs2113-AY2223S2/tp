package parser;

public class Parser {

    private static final String WHITESPACE = " ";

    public String extractCommandKeyword (String userInput) {
        String[] input = userInput.split(WHITESPACE);
        return input[0].toLowerCase();
    }

}

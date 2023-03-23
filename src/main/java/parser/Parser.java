package parser;

import java.util.ArrayList;

public class Parser {

    public static final String WHITESPACE = " ";
    private static final int EXTRACT_INDEX_LENGTH = 2;

    protected ParserAdd parserAdd = new ParserAdd();

    public String extractCommandKeyword(String userInput) {
        String[] input = userInput.split(WHITESPACE);
        return input[0].toLowerCase().trim();
    }

    /**
     * Extracts the expense index from user input.
     *
     * @param userInput User Input as a String.
     * @return Index of the expense as an Integer.
     */
    public int extractIndex(String userInput) {
        try {
            String[] input = userInput.split(WHITESPACE, EXTRACT_INDEX_LENGTH);
            return Integer.parseInt(input[1].trim());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return 0;
        }
    }

    /**
     * Extracts the indicator of "Category" and "Date" to decide the user want to sort the expenses list based on what.
     *
     * @param userInput User Input as a String.
     * @return The indicator string
     */
    public String extractSortBy(String userInput) {
        try {
            String[] input = userInput.split(WHITESPACE, EXTRACT_INDEX_LENGTH);
            System.out.println(1);
            return input[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    public String extractCommandParameters(String parameterType, String userInput) {
        int parameterTypeLength = parameterType.length();
        String[] words = userInput.split(WHITESPACE);
        ArrayList<String> results = new ArrayList<String>();
        for (String word : words) {
            if (word.startsWith(parameterType)) {
                results.add(word.substring(parameterTypeLength));
            }
        }
        return results.get(results.size() - 1);
    }

    public String[] extractAddParameters(String userInput) {
        return parserAdd.parseInput(userInput);
    }


}

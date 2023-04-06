package parser;

import java.util.ArrayList;

public class Parser {

    public static final String WHITESPACE = " ";
    private static final int EXTRACT_INDEX_LENGTH = 2;
    private static final String NO_SPECIFIC_MONTH_ERROR =
            "Please specify the month and year of the overview you intend to view.";
    protected ParserAdd parserAdd = new ParserAdd();

    public String extractCommandKeyword(String userInput) {
        String[] input = userInput.trim().split(WHITESPACE);
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
            return input[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    public String extractCategory(String userInput) {
        try {
            String[] input = userInput.split(WHITESPACE, EXTRACT_INDEX_LENGTH);
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

    /**
     * Checks if the overview requested is a monthly one.
     *
     * @param userInput as String.
     * @return true if user requires a monthly overview, false if yearly overview requested.
     */

    boolean isMonthlyOverview(String userInput) throws ArrayIndexOutOfBoundsException {
        String[] input = userInput.split(WHITESPACE);
        return input.length == 3;
    }

    public String extractMonth(String userInput) {
        String[] input = userInput.split(WHITESPACE);
        if (isMonthlyOverview(userInput)) {
            return input[1].toLowerCase().trim(); // input: ['overview', 'MONTH', 'YEAR']
        } else {
            return null;
        }
    }

    public String extractYear(String userInput){
        String[] input = userInput.split(WHITESPACE,3);
        String year = "-1";
        if (isMonthlyOverview(userInput)) {
            year = input[2].toLowerCase().trim();
        } else if (input.length == 2) {
            year = input[1].toLowerCase().trim();
        }
        return year;
    }



}

package parser;


public class ParserAdd {

    public static final int AMOUNT_INDEX = 0;
    public static final int TIME_INDEX = 1;
    public static final int CURRENCY_INDEX = 2;
    public static final int TIME_SUBSTRING_INDEX = 2;
    public static final int SUBSTRING_INDEX = 4;
    public static final int CATEGORY_INDEX = 3;
    public static final int INVALID_INDEX = -1;
    public static final int PARSED_INPUT_ARRAY_SIZE = 4;

    /**
     * Parses userInput, stores and returns the parsed input into a fixed array of size 4. Each index represents amount,
     * time, currency and category respectively.
     */
    public String[] parseInput(String userInput) {
        String[] words = userInput.split(Parser.WHITESPACE);
        String[] parsedInput = new String[PARSED_INPUT_ARRAY_SIZE];
        parsedInput[CATEGORY_INDEX] = "uncategorized";
        for (int i = words.length - 1; i >= 0; i--) {
            //loop is reversed to take the first input if duplicates occur
            int type = checkType(words[i]);
            if (type == INVALID_INDEX) {
                continue;
            }
            assert type != INVALID_INDEX;
            parsedInput[type] = words[i].substring(substringIndex(type));
        }
        return parsedInput;
    }

    /**
     * Checks the parameter type of the word, and returns the corresponding index in the parsedInput array
     */
    public int checkType(String word) {
        if (word.startsWith("amt/")) {
            return AMOUNT_INDEX;
        }
        if (word.startsWith("t/")) {
            return TIME_INDEX;
        }
        if (word.startsWith("cur/")) {
            return CURRENCY_INDEX;
        }
        if (word.startsWith("cat/")) {
            return CATEGORY_INDEX;
        } else if (!word.startsWith("add") && (!word.equals(""))) {
            System.out.println("WARNING: Invalid input type for \"" + word + "\". Please check again.");
        }
        return INVALID_INDEX;
    }

    /**
     * Checks the type and returns the substring index where the user input starts.
     */
    public int substringIndex(int type) {
        if (type == TIME_INDEX) {
            return TIME_SUBSTRING_INDEX;
        } else {
            return SUBSTRING_INDEX;
        }
    }
}

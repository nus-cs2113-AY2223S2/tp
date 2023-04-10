package seedu.parser;

import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

public class ParseIndividualValue {

    private static final int OFFSET_DELIMITER = 2;
    private static final int DOT_OFFSET_DELIMITER = 1;
    private static final String BLANK = "";
    private static final String DOT = ".";

    public static String parseIndividualValue(String userInput, String front, String back)
            throws StringIndexOutOfBoundsException, EmptyStringException {
        int positionOfFirstSlash;
        // Checks for the position of the front slash
        if (!front.equals(BLANK)) {
            positionOfFirstSlash = userInput.indexOf(front);
        } else {
            positionOfFirstSlash = -OFFSET_DELIMITER;
        }

        int positionOfSecondSlash;
        // Checks for the position of the backslash
        if (!back.equals(BLANK)) {
            positionOfSecondSlash = userInput.indexOf(back);
        } else {
            positionOfSecondSlash = userInput.length();
        }
        // Parses the value in between the front and backslash
        String value;
        if (front.equals(DOT)) {
            value = userInput.substring(positionOfFirstSlash + DOT_OFFSET_DELIMITER, positionOfSecondSlash).trim();
        } else {
            value = userInput.substring(positionOfFirstSlash + OFFSET_DELIMITER, positionOfSecondSlash).trim();
        }
        ExceptionChecker.checkEmptyString(value);
        return value;
    }
}

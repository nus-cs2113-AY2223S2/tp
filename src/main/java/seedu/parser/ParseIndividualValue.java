package seedu.parser;

import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

public class ParseIndividualValue {

    private static final int OFFSET_DELIMITER = 2;
    private static final String BLANK = "";

    public static String parseIndividualValue(String userInput, String front, String back)
            throws StringIndexOutOfBoundsException, EmptyStringException {
        int positionOfFirstSlash;
        if (!front.equals(BLANK)) {
            positionOfFirstSlash = userInput.indexOf(front);
        } else {
            positionOfFirstSlash = -OFFSET_DELIMITER;
        }
        int positionOfSecondSlash;
        if (!back.equals(BLANK)) {
            positionOfSecondSlash = userInput.indexOf(back);
        } else {
            positionOfSecondSlash = userInput.length();
        }
        String value = userInput.substring(positionOfFirstSlash + OFFSET_DELIMITER, positionOfSecondSlash).trim();
        ExceptionChecker.checkEmptyString(value);
        return value;
    }
}

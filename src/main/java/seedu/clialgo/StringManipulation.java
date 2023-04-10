package seedu.clialgo;

import seedu.clialgo.exceptions.parser.EmptyFieldException;
import seedu.clialgo.exceptions.parser.NullInputException;

//@@author heejet
/**
 * An interface used to processed user commands and extract out command names and keywords.
 */
public interface StringManipulation {
    /**
     * Extracts out the first word of an input string.
     *
     * @param userInput The input string.
     * @return The first word of the string.
     * @throws NullInputException If the userInput is empty or null.
     */
    static String getFirstWord(String userInput, String regex) throws NullInputException {
        if (userInput == null || userInput.equals("")) {
            throw new NullInputException();
        }
        String[] processedInputs = userInput.split(regex, 2);
        return processedInputs[0].trim();
    }

    /**
     * Removes the first word of an input string and returns the remaining string.
     * Returns null if the string only consist of one word.
     *
     * @param userInput The input string.
     * @return The input string with the first word removed.
     * @throws NullInputException If userInput is empty or null.
     */
    static String removeFirstWord(String userInput, String regex) throws NullInputException {
        if (userInput == null || userInput.equals("")) {
            throw new NullInputException();
        }
        String[] processedInputs = userInput.split(regex, 2);
        if (processedInputs.length <= 1 || processedInputs[1].trim().equals("")) {
            return null;
        }
        return processedInputs[1].trim();
    }

    /**
     * This method removes the marker from the keyword and returns the keyword.
     *
     * @param userInput The input with the marker at the front.
     * @param marker The marker to be removed.
     * @return userInput with the marker removed at the front.
     * @throws EmptyFieldException If the string is empty after removing the keyword.
     * @throws NullInputException If userInput is empty or null.
     */
    static String removeMarker(String userInput, String marker) throws EmptyFieldException, NullInputException {
        if (userInput == null || userInput.equals("")) {
            throw new NullInputException();
        }
        String keyword = userInput.substring(marker.length());
        if (keyword.equals("")) {
            throw new EmptyFieldException();
        }
        return keyword.trim();
    }
}

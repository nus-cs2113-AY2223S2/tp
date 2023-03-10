package seedu.duke.exceptions;

/**
 * A <code>Throwable</code> exception that occurs when
 * insufficient parameters for inputs is provided.
 */
public class IncompleteInputException extends Exception{

    /**
     * Class constructor that takes in error message as <code>inputText</code>.
     *
     * @param inputText the String representing error message to be displayed.
     */
    public IncompleteInputException(String inputText) {
        super(inputText);
    }
}

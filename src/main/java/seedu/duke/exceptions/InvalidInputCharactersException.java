package seedu.duke.exceptions;

public class InvalidInputCharactersException extends Exception{
    /**
     * Class constructor that takes in error message as <code>inputText</code>.
     *
     * @param inputText the String representing the error message to be displayed.
     */
    public InvalidInputCharactersException(String inputText) {
        super(inputText);
    }
}

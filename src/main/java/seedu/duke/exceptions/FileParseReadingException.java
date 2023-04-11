package seedu.duke.exceptions;

/**
 * A <code>Throwable</code> exception that occurs during reading
 * of saved file to obtain previously saved task list.
 */
public class FileParseReadingException extends Exception{

    /**
     * Class constructor that takes in error message as <code>inputText</code>.
     *
     * @param inputText the String representing error message to be displayed.
     */
    public FileParseReadingException(String inputText) {
        super(inputText);
    }
}

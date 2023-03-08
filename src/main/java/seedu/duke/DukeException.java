package seedu.duke;

/**
 * Class to throw exceptions specific to Duke
 * when unexpected behaviour occurs during runtime.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException that informs user about problems and errors.
     *
     * @param message The error message to be printed to user.
     */
    public DukeException(String message) {
        super(message);
    }
}

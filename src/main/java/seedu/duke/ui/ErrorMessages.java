package seedu.duke.ui;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    ERROR_GLOBAL_INVALID_COMMAND("Invalid command, please enter <help> for the command guide."),
    ERROR_EDIT_UNCHANGED("Your tags inputted does not edit the transaction.");

    //@@author chydarren
    public final String message;

    /**
     * Instantiates a new error message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the error message as a string.
     *
     * @return A string containing the message.
     */
    public String toString() {
        return message;
    }

}

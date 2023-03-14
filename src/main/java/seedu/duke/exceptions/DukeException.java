package seedu.duke.exceptions;

public class DukeException extends Exception {
    private final String errorMessage = "Error..";

    public String getMessage() {
        return errorMessage;
    }

}

package seedu.pettracker.exceptions;

/**
 * An exception thrown when the user tries to mark a complete task as complete
 */
public class TaskAlreadyCompleteException extends Exception {
    public TaskAlreadyCompleteException() {
        super("ERROR: This task is already complete!");
    }
}

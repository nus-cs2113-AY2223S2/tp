//@@author jeromeongithub
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class FailedSaveException extends ToDoListException {
    public FailedSaveException() {
        super(Errors.FAILED_SAVE);
    }
}
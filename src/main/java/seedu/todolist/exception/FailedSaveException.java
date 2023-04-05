package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

//@@author jeromeongithub
public class FailedSaveException extends ToDoListException {
    public FailedSaveException() {
        super(Errors.FAILED_SAVE);
    }
}
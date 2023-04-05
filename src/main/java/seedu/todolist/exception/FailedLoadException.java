package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

//@@author jeromeongithub
public class FailedLoadException extends ToDoListException {
    public FailedLoadException() {
        super(Errors.FAILED_LOAD);
    }
}

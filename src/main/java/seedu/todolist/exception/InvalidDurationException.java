package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidDurationException extends ToDoListException {
    public InvalidDurationException() {
        super(Errors.INVALID_DURATION.MESSAGE);
    }
}
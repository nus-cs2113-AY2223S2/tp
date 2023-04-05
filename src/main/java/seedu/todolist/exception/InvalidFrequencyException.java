package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFrequencyException extends ToDoListException {
    public InvalidFrequencyException() {
        super(Errors.INVALID_FREQUENCY);
    }
}

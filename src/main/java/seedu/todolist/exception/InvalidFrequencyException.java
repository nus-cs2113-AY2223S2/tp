package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFrequencyException extends ToDoListException {
    public InvalidFrequencyException(String frequency) {
        super(Errors.INVALID_FREQUENCY + frequency);
    }
}

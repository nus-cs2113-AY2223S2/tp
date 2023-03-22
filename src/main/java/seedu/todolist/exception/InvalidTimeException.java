package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidTimeException extends ToDoListException {
    public InvalidTimeException() {
        super(Errors.INVALID_TIME.MESSAGE);
    }
}

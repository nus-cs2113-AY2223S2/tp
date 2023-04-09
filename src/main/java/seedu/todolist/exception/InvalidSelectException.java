package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidSelectException extends ToDoListException {
    public InvalidSelectException() {
        super(Errors.INVALID_SELECT);
    }
}

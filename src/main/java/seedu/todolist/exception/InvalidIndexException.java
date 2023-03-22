package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidIndexException extends ToDoListException {
    public InvalidIndexException() {
        super(Errors.INVALID_INDEX.MESSAGE);
    }
}

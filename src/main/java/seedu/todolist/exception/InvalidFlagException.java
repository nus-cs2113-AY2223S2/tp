package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFlagException extends ToDoListException {
    public InvalidFlagException() {
        super(Errors.INVALID_FLAGS.MESSAGE);
    }
}

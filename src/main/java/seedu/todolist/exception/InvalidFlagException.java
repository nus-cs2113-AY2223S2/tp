package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFlagException extends ToDoListException {
    public InvalidFlagException(String flag) {
        super(Errors.INVALID_FLAGS + flag);
    }
}

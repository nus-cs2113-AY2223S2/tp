package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidFindException extends ToDoListException {
    public InvalidFindException() {
        super(Errors.INVALID_FIND_TAG);
    }
}

package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidEditException extends ToDoListException {
    public InvalidEditException() {
        super(Errors.INVALID_EDIT);
    }

    public InvalidEditException(int count) {
        super(Errors.INVALID_TAG_EDIT);
    }
}

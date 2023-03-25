package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidIndexException extends ToDoListException {
    public InvalidIndexException(String index) {
        super(Errors.INVALID_INDEX.getMessage() + index);
    }

    public InvalidIndexException(int index) {
        super(Errors.INVALID_INDEX.getMessage() + index);
    }
}

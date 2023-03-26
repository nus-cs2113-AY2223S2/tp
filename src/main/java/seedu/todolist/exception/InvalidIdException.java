package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidIdException extends ToDoListException {
    public InvalidIdException(String index) {
        super(Errors.INVALID_ID.getMessage() + index);
    }

    public InvalidIdException(int index) {
        super(Errors.INVALID_ID.getMessage() + index);
    }
}

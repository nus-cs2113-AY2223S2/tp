package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidIdException extends ToDoListException {
    public InvalidIdException(String id) {
        super(Errors.INVALID_ID.getMessage() + id);
    }

    public InvalidIdException(int id) {
        super(Errors.INVALID_ID.getMessage() + id);
    }
}

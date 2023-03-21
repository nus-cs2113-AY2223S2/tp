package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidDescriptionException extends ToDoListException {
    public InvalidDescriptionException() {
        super(Errors.INVALID_DESCRIPTION.MESSAGE);
    }
}

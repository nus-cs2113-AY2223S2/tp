package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidCommandException extends ToDoListException {
    public InvalidCommandException() {
        super(Errors.INVALID_COMMAND);
    }
}

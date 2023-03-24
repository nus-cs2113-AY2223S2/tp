package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidEmailFormatException extends ToDoListException {
    public InvalidEmailFormatException() {
        super(Errors.INVALID_EMAIL.MESSAGE);
    }
}

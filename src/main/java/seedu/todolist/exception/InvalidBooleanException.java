package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidBooleanException extends ToDoListException {
    public InvalidBooleanException(String boolString) {
        super(Errors.INVALID_BOOLEAN + boolString);
    }
}

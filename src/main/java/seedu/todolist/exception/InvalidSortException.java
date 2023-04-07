package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidSortException extends ToDoListException {
    public InvalidSortException(String sort) {
        super(Errors.INVALID_SORT + sort);
    }
}

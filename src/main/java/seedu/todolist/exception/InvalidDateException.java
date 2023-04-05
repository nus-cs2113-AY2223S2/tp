//@@author clement559
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidDateException extends ToDoListException {
    public InvalidDateException(String date) {
        super(Errors.INVALID_DATE + date);
    }
}

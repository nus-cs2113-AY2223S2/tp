//@@author clement559
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidDurationException extends ToDoListException {
    public InvalidDurationException(String duration) {
        super(Errors.INVALID_DURATION + duration);
    }
}

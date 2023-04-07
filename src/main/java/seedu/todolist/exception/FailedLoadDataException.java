//@@author jeromeongithub
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class FailedLoadDataException extends ToDoListException {
    public FailedLoadDataException() {
        super(Errors.FAILED_LOAD_DATA);
    }
}


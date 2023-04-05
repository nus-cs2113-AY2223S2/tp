//@@author clement559
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class FailedLoadConfigException extends ToDoListException {
    public FailedLoadConfigException() {
        super(Errors.FAILED_CONFIG_LOAD);
    }
}
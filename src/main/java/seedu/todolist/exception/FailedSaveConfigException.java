//@@author clement559
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class FailedSaveConfigException extends ToDoListException {
    public FailedSaveConfigException() {
        super(Errors.FAILED_CONFIG_SAVE);
    }
}
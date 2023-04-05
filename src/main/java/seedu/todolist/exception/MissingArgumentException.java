package seedu.todolist.exception;

import seedu.todolist.constants.Errors;
import seedu.todolist.constants.Flags;

public class MissingArgumentException extends ToDoListException {
    public MissingArgumentException(Flags flag) {
        super(Errors.MISSING_ARGUMENT + flag.getName());
    }
}

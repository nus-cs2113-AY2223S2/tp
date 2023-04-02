package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

//@@author clement559
public class PassedDateException extends ToDoListException {
    public PassedDateException() {
        super(Errors.OLD_DATE.getMessage());
    }
}

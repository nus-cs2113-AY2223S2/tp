//@@author clement559
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class PassedDateException extends ToDoListException {
    public PassedDateException(String date) {
        super(Errors.OLD_DATE + date);
    }
}

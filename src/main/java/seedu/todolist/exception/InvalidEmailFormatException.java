//@@author RuiShengGit
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class InvalidEmailFormatException extends ToDoListException {
    public InvalidEmailFormatException(String email) {
        super(Errors.INVALID_EMAIL + email);
    }
}

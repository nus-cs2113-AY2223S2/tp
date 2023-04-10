//@@author RuiShengGit
package seedu.todolist.exception;

import seedu.todolist.constants.Errors;
import seedu.todolist.constants.HelpMessages;

public class InvalidPriorityException extends ToDoListException{
    public InvalidPriorityException (String priority) {
        super(Errors.INVALID_PRIORITY + priority + System.lineSeparator() + HelpMessages.PRIORITY_HELP);
    }
}

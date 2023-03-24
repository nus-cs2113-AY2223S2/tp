package seedu.todolist.exception;

import seedu.todolist.constants.Errors;

public class EmptyEmailException extends ToDoListException{
    public EmptyEmailException() {
        super(Errors.EMPTY_EMAIL.MESSAGE);
    }
}

package seedu.todolist.exception;

public abstract class ToDoListException extends Exception {
    ToDoListException(String message) {
        super(message);
    }
}

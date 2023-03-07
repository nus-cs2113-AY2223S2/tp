package seedu.dukeofbooks.data.exception;

public class IllegalOperationException extends DukeOfBooksException{
    /**
     * @param message when an Illegal operation is being performed
     */
    public IllegalOperationException(String message) {
        super(message);
    }
}

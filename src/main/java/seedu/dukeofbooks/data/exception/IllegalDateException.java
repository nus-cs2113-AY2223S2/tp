package seedu.dukeofbooks.data.exception;

public class IllegalDateException extends DukeOfBooksException  {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalDateException(String message) {
        super(message);
    }
}

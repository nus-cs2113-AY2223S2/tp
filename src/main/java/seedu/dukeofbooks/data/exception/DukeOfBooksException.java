package seedu.dukeofbooks.data.exception;

public class DukeOfBooksException extends Exception  {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public DukeOfBooksException(String message) {
        super(message);
    }
}

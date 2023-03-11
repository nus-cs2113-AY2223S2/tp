package seedu.dukeofbooks.data.exception;


/**
 * Throws when the user wants to borrow a borrowed book or
 * return a not borrowed book.
 */
public class DuplicateActionException extends DukeOfBooksException {
    public DuplicateActionException(String msg) {
        super(msg);
    }
}

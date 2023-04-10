package utils.exceptions;

public class EditTagNameIncompleteException extends InkaException {
    public EditTagNameIncompleteException() {
        super("Please specify the old tag name and the new tag name.");
    }
}

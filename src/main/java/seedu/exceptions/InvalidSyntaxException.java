package seedu.exceptions;


public class InvalidSyntaxException extends Exception {
    private final String syntaxError;
    public InvalidSyntaxException(String syntaxError) {
        this.syntaxError = syntaxError;
    }

    @Override
    public String toString() {
        return syntaxError;
    }
}

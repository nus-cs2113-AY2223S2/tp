package utils.exceptions;

public class InvalidSyntaxException extends InkaException {

    public InvalidSyntaxException() {
        super("That command looks weird... Did you enter it correctly?");
    }
}

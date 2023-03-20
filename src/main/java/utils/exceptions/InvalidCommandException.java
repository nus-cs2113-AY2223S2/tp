package utils.exceptions;
public class InvalidCommandException extends InkaException {
    public InvalidCommandException() {
        super("Please enter a valid command!");
    }
}

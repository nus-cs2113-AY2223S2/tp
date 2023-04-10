package utils.exceptions;
public class UnrecognizedCommandException extends InkaException {
    public UnrecognizedCommandException() {
        super("Please enter a valid command!");
    }
}

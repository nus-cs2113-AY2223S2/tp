package utils.exceptions;

public class UUIDWrongFormatException extends InkaException {
    public UUIDWrongFormatException() {
        super("Please ensure that the UUID format is correct.");
    }
}

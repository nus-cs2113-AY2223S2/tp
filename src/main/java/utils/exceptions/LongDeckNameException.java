package utils.exceptions;

public class LongDeckNameException extends InkaException {

    public LongDeckNameException() {
        super("Deck name specified is too long.");
    }
}

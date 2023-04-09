package utils.exceptions;

public class LongTagNameException extends InkaException {

    public LongTagNameException() {
        super("Tag name specified is too long.");
    }
}


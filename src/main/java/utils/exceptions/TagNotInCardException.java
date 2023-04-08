package utils.exceptions;

public class TagNotInCardException extends InkaException {
    public TagNotInCardException() {
        super("Tag was not in the Card to begin with.");
    }
}

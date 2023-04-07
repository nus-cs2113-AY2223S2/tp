package utils.exceptions;

public class EmptyDeckException extends InkaException {
    public EmptyDeckException() {
        super("There is no card in this deck to be run.");
    }
}

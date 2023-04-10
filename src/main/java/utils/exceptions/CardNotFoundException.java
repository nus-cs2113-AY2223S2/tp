package utils.exceptions;

public class CardNotFoundException extends InkaException {
    public CardNotFoundException() {
        super("Card cannot be found");
    }
}

package utils.exceptions;

public class DeckNotFoundException extends InkaException {
    public DeckNotFoundException() {
        super("Deck cannot be found");
    }
}

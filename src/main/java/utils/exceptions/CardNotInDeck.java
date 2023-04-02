package utils.exceptions;

public class CardNotInDeck extends InkaException {
    public CardNotInDeck() {
        super("The card is not in the deck");
    }
}

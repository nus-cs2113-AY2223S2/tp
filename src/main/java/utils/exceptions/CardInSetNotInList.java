package utils.exceptions;

public class CardInSetNotInList extends InkaException{
    public CardInSetNotInList() {
        super("Card is present in the deck under a tag, hence cannot be deleted this way");
    }
}

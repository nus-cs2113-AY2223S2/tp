package utils.exceptions;

public class CardInDeckUnderTagException extends InkaException{
    public CardInDeckUnderTagException() {
        super("Card is already part of deck under a tag");
    }
}

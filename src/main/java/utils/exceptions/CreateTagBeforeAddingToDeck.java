package utils.exceptions;

public class CreateTagBeforeAddingToDeck extends InkaException{
    public CreateTagBeforeAddingToDeck() {
        super("Please ensure that you create a tag first!");
    }
}

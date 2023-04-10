package utils.exceptions;

public class TagCardIncompleteInput extends InkaException {
    public TagCardIncompleteInput() {
        super("Please ensure that you have entered the card UUID and the tag name that you wish to tag the card with.");
    }
}

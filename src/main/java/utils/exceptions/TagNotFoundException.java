package utils.exceptions;

public class TagNotFoundException extends InkaException {
    public TagNotFoundException() {
        super("Tag cannot be found");
    }
}

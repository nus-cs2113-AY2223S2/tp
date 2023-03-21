package utils.exceptions;

public class UnknownItem extends InkaException {
    public UnknownItem() {
        super("Can't find what you're referring to...");
    }
}

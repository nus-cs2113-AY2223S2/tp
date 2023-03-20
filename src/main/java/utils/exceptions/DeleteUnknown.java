package utils.exceptions;

public class DeleteUnknown extends InkaException {
    public DeleteUnknown() {
        super("Can't find what you're referring to...");
    }
}

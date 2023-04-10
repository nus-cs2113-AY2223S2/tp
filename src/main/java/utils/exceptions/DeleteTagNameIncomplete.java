package utils.exceptions;

public class DeleteTagNameIncomplete extends InkaException {
    public DeleteTagNameIncomplete() {
        super("Please specify the tag name of the tag that you wish to delete.");
    }
}

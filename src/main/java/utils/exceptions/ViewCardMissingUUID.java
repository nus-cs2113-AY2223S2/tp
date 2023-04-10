package utils.exceptions;

public class ViewCardMissingUUID extends InkaException {
    public ViewCardMissingUUID() {
        super("Please specify the uuid of the card that you wish to view");
    }
}

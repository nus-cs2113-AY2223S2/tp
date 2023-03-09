package seedu.BrokeMan.command;

public class resultCommand {
    public final String OUTPUT_MESSAGE_TO_USER;

    public resultCommand(String messageToUser) {
        this.OUTPUT_MESSAGE_TO_USER = messageToUser;
    }

    @Override
    public String toString() {
        return OUTPUT_MESSAGE_TO_USER;
    }
}

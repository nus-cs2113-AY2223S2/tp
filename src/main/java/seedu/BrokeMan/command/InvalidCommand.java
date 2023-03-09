package seedu.BrokeMan.command;

public class InvalidCommand extends Command {
    private String invalidMessage;

    public InvalidCommand(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }

    public void execute() {
        System.out.println(invalidMessage);
    }
}

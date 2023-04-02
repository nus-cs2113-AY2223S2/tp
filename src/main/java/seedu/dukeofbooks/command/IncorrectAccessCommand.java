package seedu.dukeofbooks.command;

public class IncorrectAccessCommand extends AccessCommand {
    private static final String UNKNOWN_CMD = "Unknown Command!";

    public final String feedbackToUser;

    public IncorrectAccessCommand(String feedbackToUser) {
        super(null);
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public AccessResponse execute() {
        return new AccessResponse(null, UNKNOWN_CMD);
    }

    public static boolean isIncorrect(Command command) {
        return command instanceof IncorrectAccessCommand;
    }
}

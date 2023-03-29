package seedu.dukeofbooks.command;

public class IncorrectUserCommand extends UserCommand {
    public final String feedbackToUser;

    public IncorrectUserCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}

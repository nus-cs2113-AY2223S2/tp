package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;

public class IncorrectAccessCommand extends AccessCommand {
    public final String feedbackToUser;

    public IncorrectAccessCommand(String feedbackToUser) {
        super(null);
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public User execute() throws IllegalValueException {
        throw new IllegalValueException(feedbackToUser);
    }

    public static boolean isIncorrect(Command command) {
        return command instanceof IncorrectAccessCommand;
    }
}

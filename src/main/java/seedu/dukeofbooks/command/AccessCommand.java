package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

public abstract class AccessCommand extends Command {
    protected UserRecords userRecords;

    public AccessCommand(UserRecords userRecords) {
        this.userRecords = userRecords;
    }

    public abstract User execute() throws IllegalValueException;

    public boolean isAccessCommand() {
        return true;
    }

    public static boolean isAccessCommand(Command command) {
        return command instanceof AccessCommand;
    }
}

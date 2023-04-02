package seedu.dukeofbooks.command;

import seedu.dukeofbooks.data.user.UserRecords;

public abstract class AccessCommand extends Command {
    protected UserRecords userRecords;

    public AccessCommand(UserRecords userRecords) {
        this.userRecords = userRecords;
    }

    public abstract AccessResponse execute();

    public boolean isAccessCommand() {
        return true;
    }

    public static boolean isAccessCommand(Command command) {
        return command instanceof AccessCommand;
    }
}

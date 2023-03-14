package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;

public class Command {
    private final boolean isExit;

    public Command() {
        this(false);
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(EntryLog entries) throws Exception {
    }

    public boolean getIsExit() {
        return isExit;
    }
}

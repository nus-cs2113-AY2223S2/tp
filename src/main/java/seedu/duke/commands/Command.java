package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;
import seedu.duke.storage.Storage;

public class Command {
    private final boolean isExit;

    public Command() {
        this(false);
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(EntryLog entries, Storage storage) throws Exception {
        executor(entries);
        storage.writeToDatabase(entries.getEntries());
    }

    public void executor(EntryLog entries) throws Exception {
    }

    public boolean getIsExit() {
        return isExit;
    }
}

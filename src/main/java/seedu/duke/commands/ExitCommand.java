package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void executor(EntryLog entries) {
    }
}

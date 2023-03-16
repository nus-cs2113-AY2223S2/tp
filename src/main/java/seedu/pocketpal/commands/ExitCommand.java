package seedu.pocketpal.commands;

import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void executor(EntryLog entries, UI ui) {
    }
}

package seedu.pocketpal.commands;

import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.ui.UI;


public class HelpCommand extends Command {
    @Override
    public void executor(EntryLog entry, UI ui) {
        ui.printHelp();
    }
}



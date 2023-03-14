package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;
import seedu.duke.ui.UI;


public class HelpCommand extends Command {
    @Override
    public void executor(EntryLog entry) {
        UI ui = new UI();
        ui.printHelp();
    }
}



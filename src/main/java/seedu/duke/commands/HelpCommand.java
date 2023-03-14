package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;
import seedu.duke.ui.UI;


public class HelpCommand extends Command{
    @Override
    public void execute(EntryLog entry){
        UI ui =new UI();
        ui.printHelp();
    }
}



package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.Parser;

import java.util.ArrayList;

public class ExitCommand extends Command {
    @Override
    public void execute() {
        setExit(true);
        ui.printExitMessage();
    }
}

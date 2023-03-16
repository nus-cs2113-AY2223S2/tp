package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class InvalidCommand extends Command {
    @Override
    public void execute() {
        ui.printInvalidInputMessage();
    }
}

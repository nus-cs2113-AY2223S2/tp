package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListPuCommand extends Command {
    @Override
    public void execute() {
        ui.printPUList();
        ui.printPUListMessage();
    }

}

package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListCurrentCommand extends Command {
    private ArrayList<Module> modules;

    public ListCurrentCommand(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * Executes ListCurrentCommand, where it calls UI class to print out all user added modules
     * for all Partner Universities.
     */
    @Override
    public void execute() {
        ui.printAllCurrentModList(modules);
    }
}

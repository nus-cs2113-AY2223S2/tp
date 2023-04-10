package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListCurrentPuCommand extends Command {
    private ArrayList<Module> modules;
    private int univId;

    public ListCurrentPuCommand(ArrayList<Module> modules, int univId) {
        this.modules = modules;
        this.univId = univId;
    }

    /**
     * Executes ListCurrentPuCommand where it calls UI class to print out user added modules for the
     * given Partner University identified by the univID.
     */
    @Override
    public void execute() {
        ui.printCurrentPuModList(modules, univId);
    }
}

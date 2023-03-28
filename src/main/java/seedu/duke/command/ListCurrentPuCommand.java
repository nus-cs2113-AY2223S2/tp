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

    @Override
    public void execute() {
        ui.printCurrentPuModList(modules, univId);
    }
}

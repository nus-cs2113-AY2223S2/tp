package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListCurrentCommand extends Command {
    private ArrayList<Module> modules;

    public ListCurrentCommand(ArrayList<Module> modules) {
        this.modules = modules;
    }

    @Override
    public void execute() {
        ui.printCurrentListMessage();
        ui.printCurrentModList(modules);
    }
}

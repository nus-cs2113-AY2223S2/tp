package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListMappableNusModsCommand extends Command{
    private ArrayList<Module> allModules;
    public  ListMappableNusModsCommand(ArrayList<Module> allModules) {
        this.allModules = allModules;
    }

    @Override
    public void execute() {
        ui.printNusMods(allModules);
        ui.getLine();
    }

}

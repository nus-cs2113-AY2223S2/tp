package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListMappableNusModsCommand extends Command{
    private ArrayList<Module> allModules;
    public  ListMappableNusModsCommand(ArrayList<Module> allModules) {
        this.allModules = allModules;
    }

    /**
     * Executes ListMappableNusModsCommand, where it calls UI class to print out a list the available Nus Modules to
     * search by for mappable Partner University modules.
     */
    @Override
    public void execute() {
        ui.printNusMods(allModules);
        ui.getLine();
    }

}

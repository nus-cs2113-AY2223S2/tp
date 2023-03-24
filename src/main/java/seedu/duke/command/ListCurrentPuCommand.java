package seedu.duke.command;

import seedu.duke.DataReader;
import seedu.duke.Module;
import seedu.duke.University;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class ListCurrentPuCommand extends Command {
    private ArrayList<Module> modules;

    public ListCurrentPuCommand(ArrayList<Module> modules, int univId) {
        ArrayList<Module> puModulesToPrint = new ArrayList<>();
        for (int i = 0; i < modules.size(); i++) {
            Module currentModule = modules.get(i);
            int currentModuleUnivId = currentModule.getUnivId();
            if (currentModuleUnivId == univId) {
                puModulesToPrint.add(currentModule);
            }

        }
        this.modules = puModulesToPrint;
    }

    @Override
    public void execute() {
        ui.printCurrentModList(modules);
    }
}
package seedu.apollo.module;

import seedu.apollo.exception.module.ModuleNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * ModuleList class is a modified ArrayList of Modules.
 */
public class ModuleList extends ArrayList<Module> {

    /**
     * Finds the module in the ModuleList which matches the module name.
     *
     * @param moduleCode The code of the module to be found.
     * @return module in the ModuleList which matches the module name.
     * @throws ModuleNotFoundException If the moduleCode is not found in allModules.
     */
    public Module findModule(String moduleCode) {
        Module module = this.stream().filter(mod -> mod.getCode().equalsIgnoreCase(moduleCode))
                .findFirst().orElse(null);

        return module;
    }

    /**
     * Sorts the ModuleList in alphabetical order.
     */
    public void sortModules() {
        this.sort(Comparator.comparing(Module::getCode));
    }

    /**
     * Calculate the total modular credit in ArrayList of all modules.
     *
     * @return Total modular credits.
     */
    public int getTotalModuleCredits() {
        int totalSemesterCredits = 0;
        for (Module module : this) {
            totalSemesterCredits += Integer.parseInt(module.getModuleCredits());
        }
        return totalSemesterCredits;
    }

}

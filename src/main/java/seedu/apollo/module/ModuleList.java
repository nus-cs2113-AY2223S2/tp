package seedu.apollo.module;

import seedu.apollo.exception.ModuleNotFoundException;

import java.util.ArrayList;

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
    public Module findModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : this) {
            if(module.getCode().toLowerCase().equals(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * Removes the module in the ModuleList.
     *
     * @param module The module to be deleted.
     */
    public void deleteModule(Module module) {
        this.remove(module);
    }

    /**
     * Adds the module to the ModuleList.
     *
     * @param module The module to be added.
     */
    public void addModule(Module module) {
        this.add(module);
    }

}

package seedu.apollo.module;

import seedu.apollo.exception.ModuleNotFoundException;

import java.util.ArrayList;

/**
 * ModuleList class that contains the module list.
 */
public class ModuleList {

    // Module list containing all Modules.
    public ArrayList<Module> allModules;

    /**
     * Initialise allModules with the given Arraylist.
     *
     * @param allModules List of Modules.
     */
    public ModuleList(ArrayList<Module> allModules) {
        this.allModules = allModules;
    }

    /**
     * Get the list of all Modules.
     *
     * @return ArrayList of allModules.
     */
    public ArrayList<Module> getAllModules() {
        return allModules;
    }

    /**
     * Get the number of Modules currently in the ModuleList.
     *
     * @return Number of existing Modules.
     */
    public int getModuleListSize() {
        return allModules.size();
    }

    /**
     * Finds the module in the ModuleList which matches the module name.
     *
     * @param moduleCode The code of the module to be found.
     * @return module in the ModuleList which matches the module name.
     * @throws ModuleNotFoundException If the moduleCode is not found in allModules.
     */
    public Module findModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : allModules) {
            if(module.getCode().equals(moduleCode)) {
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
        allModules.remove(module);
    }

    /**
     * Adds the module to the ModuleList.
     *
     * @param module The module to be added.
     */
    public void addModule(Module module) {
        this.allModules.add(module);
    }

}

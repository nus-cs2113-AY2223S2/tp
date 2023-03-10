package seedu.apollo.module;

import java.util.ArrayList;

/**
 * ModuleList class that contains the module list.
 */
public class ModuleList {
    /**
     * Module list containing all Modules.
     */
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

    public void addModule(Module module) {
        this.allModules.add(module);
    }
}

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

    /**
     * Finds the module in the ModuleList which matches the module name
     * @param moduleName The name of the module to be found
     * @return module in the ModuleList which matches the module name 
     */
    public Module findModule(String moduleName) {

    for (Module module : allModules) {
        if (module.getModuleCode.equals(moduleName)) {
        return module;
        }
    }
        return null;
    }
    /**
     * Removes the module in the ModuleList
     * @param module The module to be deleted
     */
    public void deleteModule(Module module){
        allModules.remove(module);
    }
}

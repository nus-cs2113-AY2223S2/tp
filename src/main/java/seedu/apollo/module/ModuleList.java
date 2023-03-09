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
     * Finds the module in the ModuleList which matches the keyword
     * @param keyword The name of the module to be found
     * @return module in the ModuleList which matches the keyword
     */
    public Module findModule(String keyword) {

        for(int i=0;i<allModules.size();i++){
            Module toFind = allModules.get(i);
            if(toFind.equals(keyword)){
                return toFind;

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

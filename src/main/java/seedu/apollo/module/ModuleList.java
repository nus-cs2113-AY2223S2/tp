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
    public Module findModule(String moduleCode){
        Module module = this.stream().filter(mod -> mod.getCode().equalsIgnoreCase(moduleCode))
                .findFirst().orElse(null);

        return module;
    }

}

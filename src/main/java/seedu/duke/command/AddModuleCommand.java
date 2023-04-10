package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.ModuleStorage;

public class AddModuleCommand extends Command {

    private Module moduleToAdd;
    private ModuleStorage storage;

    public AddModuleCommand(Module module, ModuleStorage storage) {
        moduleToAdd = module;
        this.storage = storage;
    }

    public Module getModuleToAdd() {
        return moduleToAdd;
    }

    public ModuleStorage getStorage() {
        return storage;
    }

    /**
     * Executes the AddModuleCommand where it calls the ModuleStorage class to add a module to the saved module list.
     * Prints an add module successfully message if the module adding in storage is successful.l
     */
    @Override
    public void execute() {
        boolean isAddSuccessful = storage.addModuleToModuleList(moduleToAdd);
        if (!isAddSuccessful) {
            return;
        }
        ui.printAddModMessage(moduleToAdd);
    }
}

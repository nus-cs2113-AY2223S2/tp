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

    @Override
    public void execute() {
        boolean isAddSuccessful = storage.addModuleToModuleList(moduleToAdd);
        if (!isAddSuccessful) {
            return;
        }
        ui.printAddModMessage(moduleToAdd);
    }
}

package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.ModueStorage;

public class AddModuleCommand extends Command {

    private Module moduleToAdd;
    private ModueStorage storage;

    public AddModuleCommand(Module module, ModueStorage storage) {
        moduleToAdd = module;
        this.storage = storage;
    }

    public Module getModuleToAdd() {
        return moduleToAdd;
    }

    public ModueStorage getStorage() {
        return storage;
    }

    @Override
    public void execute() {
        boolean isAddSuccessful = storage.addModuleToModuleList(moduleToAdd);
        if (!isAddSuccessful) {
            return;
        }
        ui.printAddModMessage();
    }
}

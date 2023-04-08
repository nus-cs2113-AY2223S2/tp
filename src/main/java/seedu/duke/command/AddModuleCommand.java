package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.Storage;

public class AddModuleCommand extends Command {

    private Module moduleToAdd;
    private Storage storage;

    public AddModuleCommand(Module module, Storage storage) {
        moduleToAdd = module;
        this.storage = storage;
    }

    public Module getModuleToAdd() {
        return moduleToAdd;
    }

    public Storage getStorage() {
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

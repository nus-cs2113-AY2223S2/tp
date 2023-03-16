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

    @Override
    public void execute() {
        ui.printAddModMessage();
        storage.addModuleToModuleList(moduleToAdd);
    }
}

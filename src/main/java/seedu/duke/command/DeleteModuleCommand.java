package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.ModuleStorage;

import java.util.ArrayList;

public class DeleteModuleCommand extends Command {

    private ModuleStorage storage;

    private int indexToRemove;

    private ArrayList<Module> modules;
    private int uniID;

    public DeleteModuleCommand(ModuleStorage storage, int indexToRemove, int uniID) {
        this.storage = storage;
        this.indexToRemove = indexToRemove;
        this.modules = storage.getModules();
        this.uniID = uniID;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = ModuleStorage.deleteModule(indexToRemove, modules, storage, uniID);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteModMessage();
    }
}

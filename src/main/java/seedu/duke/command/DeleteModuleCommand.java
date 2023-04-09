package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.ModueStorage;

import java.util.ArrayList;

public class DeleteModuleCommand extends Command {

    private ModueStorage storage;

    private int indexToRemove;

    private ArrayList<Module> modules;
    private int uniID;

    public DeleteModuleCommand(ModueStorage storage, int indexToRemove, int uniID) {
        this.storage = storage;
        this.indexToRemove = indexToRemove;
        this.modules = storage.getModules();
        this.uniID = uniID;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = ModueStorage.deleteModule(indexToRemove, modules, storage, uniID);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteModMessage();
    }
}

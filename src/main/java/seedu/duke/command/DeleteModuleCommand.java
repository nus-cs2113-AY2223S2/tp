package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.Storage;

import java.util.ArrayList;

public class DeleteModuleCommand extends Command {

    private Storage storage;

    private int indexToRemove;

    private ArrayList<Module> modules;
    private int uniID;

    public DeleteModuleCommand(Storage storage, int indexToRemove, int uniID) {
        this.storage = storage;
        this.indexToRemove = indexToRemove;
        this.modules = storage.getModules();
        this.uniID = uniID;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = Storage.deleteModule(indexToRemove, modules, storage, uniID);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteModMessage();
    }
}

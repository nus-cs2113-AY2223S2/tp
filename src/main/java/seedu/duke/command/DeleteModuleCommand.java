package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.Parser;
import seedu.duke.Storage;

import java.util.ArrayList;

public class DeleteModuleCommand extends Command {

    private Storage storage;

    private int indexToRemove;

    private ArrayList<Module> modules;

    public DeleteModuleCommand(Storage storage, int indexToRemove, ArrayList<Module> modules) {
        this.storage = storage;
        this.indexToRemove = indexToRemove;
        this.modules = modules;
    }

    @Override
    public void execute() {
        boolean isDeleteSuccessful = Parser.handleDeleteModule(indexToRemove, modules, storage);
        if (!isDeleteSuccessful) {
            return;
        }
        ui.printDeleteModMessage();
    }
}

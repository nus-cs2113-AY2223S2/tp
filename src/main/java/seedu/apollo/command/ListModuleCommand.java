package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;

public class ListModuleCommand extends Command{
    public void execute(ModuleList modules, Ui ui, Storage storage) {
        ui.printModuleList(modules.getAllModules());
    }
}

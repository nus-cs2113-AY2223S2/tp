package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;
import seedu.apollo.task.TaskList;

public class ListModuleCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        ui.printModuleList(moduleList.getAllModules());
    }
}

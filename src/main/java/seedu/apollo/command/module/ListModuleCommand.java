package seedu.apollo.command.module;

import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.ui.Ui;
import seedu.apollo.task.TaskList;

public class ListModuleCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        assert taskList != null : "TaskList should not be null!";
        assert ui != null : "Ui should not be null!";
        assert storage != null : "Storage should not be null!";
        assert moduleList != null : "ModuleList should not be null!";
        ui.printModuleList(moduleList);
    }
}

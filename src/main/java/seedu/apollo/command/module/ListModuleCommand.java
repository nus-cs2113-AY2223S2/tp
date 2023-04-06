package seedu.apollo.command.module;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.ui.Ui;
import seedu.apollo.task.TaskList;

import java.util.logging.Logger;

public class ListModuleCommand extends Command {
    private static Logger logger = Logger.getLogger("ListModuleCommand");

    public ListModuleCommand() {
        super(logger);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        assert (moduleList != null) : "ListModuleCommand: ModuleList should not be null!";
        ui.printModuleList(moduleList);
    }
}

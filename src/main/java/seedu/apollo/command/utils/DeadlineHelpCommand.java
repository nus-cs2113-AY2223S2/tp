package seedu.apollo.command.utils;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

public class DeadlineHelpCommand extends HelpCommand{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printDeadlineHelpMessage();
    }
}


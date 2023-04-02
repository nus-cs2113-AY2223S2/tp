package seedu.apollo.command;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

/**
 * Week Command class that displays the user's schedule for the week
 */
public class WeekCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printWeek(taskList, calendar);
    }

}

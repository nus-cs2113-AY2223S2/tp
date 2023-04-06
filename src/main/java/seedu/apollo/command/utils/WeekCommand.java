package seedu.apollo.command.utils;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;

/**
 * Week Command class that displays the user's schedule for the week
 */
public class WeekCommand extends Command {

    public WeekCommand() {
        super(Logger.getLogger("WeekCommand"));
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ZoneId zid = ZoneId.of("Asia/Singapore");
        LocalDate now = LocalDate.now(zid);
        LocalDate startWeek = now.with(DayOfWeek.MONDAY);
        LocalDate endWeek = now.with(DayOfWeek.SUNDAY);
        ui.printWeek(taskList, calendar, startWeek, endWeek);
    }

}

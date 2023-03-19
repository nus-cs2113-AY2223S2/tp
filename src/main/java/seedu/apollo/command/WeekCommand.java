package seedu.apollo.command;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.module.CalendarModule;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import static seedu.apollo.utils.DayTypeUtil.determineDay;

/**
 * Week Command class that displays the user's timetable for the week
 */
public class WeekCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        System.out.println("Here's your week: ");
        for (int i = 0; i<7; i++) {
            System.out.println("______________________");
            System.out.println(determineDay(i) + ":");
            int count = 1;
            for (CalendarModule module : calendar.get(i)) {
                System.out.println(count + ". " + module.getCode() + " " + module.getSchedule());
                count++;
            }
        }
    }

}

//@@author jeromeongithub
package seedu.todolist.logic.command;

import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.function.Predicate;

public class ProgressBarCommand extends Command {
    private static final int PROGRESS_BAR_SECTIONS = 50;

    @Override
    public void execute(TaskList taskList, Ui ui) {
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        // Before Monday (start of the week - 1st day)
        LocalDate beforeStartOfWeek = LocalDate.now().with(fieldISO, 1).minusDays(1);
        // After Sunday (end of the week - 7th day)
        LocalDate afterEndOfWeek = beforeStartOfWeek.plusDays(8);
        Predicate<Task> inThisWeek = Task.afterDate(beforeStartOfWeek).and(Task.beforeDate(afterEndOfWeek));

        int tasksThisWeek = taskList.size(inThisWeek);
        int completedTasksThisWeek = taskList.size(inThisWeek.and(Task.isDonePredicate()));
        ui.printProgressBar(completedTasksThisWeek, tasksThisWeek,
                PROGRESS_BAR_SECTIONS, taskList.toString(inThisWeek));
    }
}

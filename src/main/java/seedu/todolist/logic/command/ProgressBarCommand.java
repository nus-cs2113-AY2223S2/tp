package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

//@@author jeromeongithub
public class ProgressBarCommand extends Command {
    private static final int PROGRESS_BAR_SECTIONS = 50;

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        // Monday (start of the week - 1st day)
        LocalDate startOfWeek = LocalDate.now().with(fieldISO, 1);
        // Sunday (end of the week - 7th day)
        LocalDate endOfWeek = LocalDate.now().with(fieldISO, 7);

        int tasksThisWeek = 0;
        int completedTasksThisWeek = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.getTask(i);
            LocalDate dueDate = currentTask.getDeadline().toLocalDate();
            boolean isDueThisWeek = (dueDate.isEqual(startOfWeek) ||
                    (dueDate.isAfter(startOfWeek) && dueDate.isBefore(endOfWeek)) ||
                    dueDate.isEqual(endOfWeek));
            boolean isCompleted = currentTask.isDone();
            if (isDueThisWeek) {
                tasksThisWeek++;
            }
            if (isCompleted) {
                completedTasksThisWeek++;
            }
        }
        ui.printProgressBar(completedTasksThisWeek, tasksThisWeek, PROGRESS_BAR_SECTIONS);
    }
}

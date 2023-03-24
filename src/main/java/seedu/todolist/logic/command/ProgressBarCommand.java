package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class ProgressBarCommand extends Command {
    public static final String KEYWORD = "progress";
    public static final int TOTAL_NUMBER_OF_SECTIONS_FOR_PROGRESS_BAR = 50;
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate startOfWeek = LocalDate.now().with(fieldISO,1); // Monday (start of the week - 1st day)
        LocalDate endOfWeek = LocalDate.now().with(fieldISO,7); // Sunday (end of the week - 7th day)
        int totalNumberOfTasksThisWeek = 0;
        int numberOfCompletedTasksThisWeek = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.getTask(i);
            LocalDate dueDate = currentTask.getDeadline().toLocalDate();
            boolean isDueThisWeek = (dueDate.isEqual(startOfWeek) ||
                    (dueDate.isAfter(startOfWeek) && dueDate.isBefore(endOfWeek)) ||
                    dueDate.isEqual(endOfWeek));
            boolean isCompleted = currentTask.isDone();
            if (isDueThisWeek) {
                totalNumberOfTasksThisWeek++;
            }
            if (isCompleted) {
                numberOfCompletedTasksThisWeek++;
            }
        }
        ui.printProgressBar(totalNumberOfTasksThisWeek, numberOfCompletedTasksThisWeek);
    }
}

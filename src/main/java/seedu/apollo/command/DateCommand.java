package seedu.apollo.command;

import seedu.apollo.Ui;
import seedu.apollo.exception.InvalidDateTime;
import seedu.apollo.Storage;
import seedu.apollo.task.Task;
import seedu.apollo.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Date Command class that shortlists Tasks that occur on the given date.
 */
public class DateCommand extends Command {

    LocalDate date;

    /**
     * Initialises the class with the given date to shortlist for.
     *
     * @param dateString User input of the date, should be in format {@code yyyy-MM-dd}.
     * @throws InvalidDateTime If the input date does not fit the above format.
     */
    public DateCommand (String dateString) throws InvalidDateTime {
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTime();
        }
    }

    /**
     * Shortlists and prints Tasks from the TaskList that occur during the given date.
     *
     * @param tasks The existing TaskList.
     * @param ui Prints shortlisted Tasks to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksOnDate = new TaskList();
        for (Task task : tasks) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }
        ui.printDateList(tasksOnDate, date);
    }

}

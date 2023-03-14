package seedu.apollo.command.task;

import seedu.apollo.ui.Ui;
import seedu.apollo.command.Command;
import seedu.apollo.exception.task.InvalidDateTime;
import seedu.apollo.storage.Storage;
import seedu.apollo.module.ModuleList;
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
        assert dateString != null : "Date string should not be null";
        assert dateString.matches("\\d{4}-\\d{2}-\\d{2}") : "Date string should be in format yyyy-MM-dd";
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTime();
        }
    }

    /**
     * Shortlists and prints Tasks from the TaskList that occur during the given date.
     *
     * @param taskList The existing TaskList.
     * @param ui Prints shortlisted Tasks to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        ui.printDateList(taskList.getTasksOnDate(date), date);
    }

}

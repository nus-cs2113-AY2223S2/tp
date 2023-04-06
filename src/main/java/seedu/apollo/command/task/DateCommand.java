package seedu.apollo.command.task;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.ui.Ui;
import seedu.apollo.command.Command;
import seedu.apollo.exception.task.InvalidDateTime;
import seedu.apollo.storage.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date Command class that shortlists Tasks that occur on the given date.
 */
public class DateCommand extends Command {
    LocalDate date;
    DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Initialises the class with the given date to shortlist for.
     *
     * @param dateString User input of the date, should be in format {@code dd-MM-yyyy}.
     * @throws InvalidDateTime If the input date does not fit the above format.
     */
    public DateCommand (String dateString) throws InvalidDateTime {
        super("DateCommand");
        assert dateString != null : "DateCommand: dateString should not be null!";
        try {
            this.date = LocalDate.parse(dateString,datePattern);
            assert dateString.matches("\\d{2}-\\d{2}-\\d{4}") : "Date string should be in format dd-MM-yyyy";
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
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printDateList(taskList.getTasksOnDate(date), date);
    }

}

package seedu.apollo.task;

import seedu.apollo.exception.task.DateOverException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadlines are a type of Task that have a set due date,
 * along with the default description and status.
 */
public class Deadline extends Task {

    public static final String DEADLINE_LABEL = "D";
    protected LocalDateTime by;

    /**
     * Initialises as in Task, with added parsing for due date.
     * If parsing is not possible, save due date as String.
     *
     * @param description String describing the Task.
     * @param byString String describing the due date.
     * @throws DateTimeParseException If due date is not entered in right format.
     * @throws DateOverException If due date occurs before the current date.
     */
    public Deadline(String description, String byString)
            throws DateTimeParseException, DateOverException {
        super(description);
        this.by = LocalDateTime.parse(byString,storePattern);
        if (by.isBefore(LocalDateTime.now())) {
            throw new DateOverException(getType(), description, by, null, null);
        }

    }

    /**
     * Get a String describing the due date of the Deadline.
     *
     * @param pattern Desired format for String after parsing.
     * @return Parsed due date.
     */
    public String getBy(DateTimeFormatter pattern) {
        return by.format(pattern);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String getType() {
        return "deadline";
    }

    public LocalDateTime getByDate() {
        return by;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOnDate(LocalDate date) {
        LocalDate byDate = by.toLocalDate();
        return date.isEqual(byDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "][" + getStatus() + "] " + description + " (by: " + getBy(printPattern) + ")";
    }

}

package seedu.apollo.task;

import seedu.apollo.ui.Parser;

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
    protected String byString;

    /**
     * Initialises as in Task, with added parsing for due date.
     * If parsing is not possible, save due date as String.
     *
     * @param description String describing the Task.
     * @param byString String describing the due date.
     */
    public Deadline(String description, String byString) {
        super(description);
        try {
            this.by = LocalDateTime.parse(byString);
        } catch (DateTimeParseException e) {
            this.byString = byString;
        }
    }

    /**
     * Get a String describing the due date of the Deadline.
     *
     * @param pattern Desired format for String after parsing.
     * @return Parsed due date.
     */
    public String getBy(DateTimeFormatter pattern) {
        assert (by != null | byString != null)  : "Deadline must have a due date";
        return Parser.parseDateTime(by, byString, pattern);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOnDate(LocalDate date) {
        boolean byExists = (by != null);
        LocalDate byDate = null;
        if (byExists) {
            byDate = by.toLocalDate();
        }
        return byExists && date.isEqual(byDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "][" + getStatus() + "] " + description + " (by: " + getBy(printPattern) + ")";
    }

}

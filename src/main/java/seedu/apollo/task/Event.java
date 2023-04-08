package seedu.apollo.task;

import seedu.apollo.exception.task.DateOverException;
import seedu.apollo.exception.task.DateOrderException;




import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



/**
 * Events are a type of Task that have a set start date and end date,
 * along with the default description and status.
 */
public class Event extends Task {
    public static final String EVENT_LABEL = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initialises as in Task, with added parsing for start and end dates.
     * If parsing is not possible, save date(s) as String(s).
     *
     * @param description String describing the Task.
     * @param fromString String describing the start date.
     * @param toString String describing the end date.
     * @throws DateTimeParseException If either date is not entered in right format.
     * @throws DateOrderException If end date occurs before the start date.
     * @throws DateOverException If end date occurs before the current date.
     */
    public Event(String description, String fromString, String toString)
            throws DateTimeParseException, DateOrderException, DateOverException {
        super(description);
        this.from = LocalDateTime.parse(fromString,storePattern);
        this.to = LocalDateTime.parse(toString,storePattern);

        if (from.isAfter(to)) {
            throw new DateOrderException();
        }

        if (to.isBefore(LocalDateTime.now())) {
            throw new DateOverException(getType(), description, null, from, to);
        }


    }

    /**
     * Get a String describing the start date of the Event.
     *
     * @param pattern Desired format for String after parsing.
     * @return Parsed start date.
     */
    public String getFrom(DateTimeFormatter pattern) {
        return from.format(pattern);
    }

    /**
     * Get a String describing the end date of the Event.
     *
     * @param pattern Desired format for String after parsing.
     * @return Parsed end date.
     */
    public String getTo(DateTimeFormatter pattern) {
        return to.format(pattern);
    }

    public LocalDateTime getFromDate() {
        return from;
    }

    public LocalDateTime getToDate() {
        return to;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOnDate(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        boolean isBefore = date.isBefore(fromDate);
        boolean isAfter = date.isAfter(toDate);
        return !(isBefore | isAfter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "][" + getStatus() + "] " + description +
                " (from: " + getFrom(printPattern) + " to: " + getTo(printPattern) + ")";
    }


}


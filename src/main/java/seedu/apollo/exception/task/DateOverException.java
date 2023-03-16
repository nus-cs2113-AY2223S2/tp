package seedu.apollo.exception.task;

import java.time.LocalDateTime;

import static seedu.apollo.task.Task.printPattern;
import static seedu.apollo.ui.Parser.COMMAND_DEADLINE_WORD;
import static seedu.apollo.ui.Parser.COMMAND_EVENT_WORD;

/**
 * Exception class for when the Task being added occurs before the current date.
 */
public class DateOverException extends Throwable {

    private final String type;
    private final String description;
    private final LocalDateTime by;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Initialises the exception with details of the Task that was not successfully added.
     *
     * @param type Type of task (Deadline or Event).
     * @param description Description of task.
     * @param by Due date (if task is Deadline).
     * @param from Start date (if task is Event).
     * @param to End date (if task is Event).
     */
    public DateOverException(String type, String description, LocalDateTime by, LocalDateTime from, LocalDateTime to) {
        this.type = type;
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        assert (type.equals("deadline") | type.equals("event"));
        switch (type) {
        case COMMAND_DEADLINE_WORD:
            return type + ": " + description + " (by: " + by.format(printPattern) + ")";
        case COMMAND_EVENT_WORD:
            return type + ": " + description + " (from: " + from.format(printPattern) +
                    " to: " + to.format(printPattern) + ")";
        default:
            return "";
        }
    }

}

package seedu.apollo.exception.task;

import seedu.apollo.ui.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.apollo.task.Task.printPattern;

public class DateOverException extends Throwable {

    String type;
    String description;
    LocalDateTime by;
    LocalDateTime from;
    LocalDateTime to;

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
        case "deadline":
            return type + ": " + description + " (by: " + by.format(printPattern) + ")";
        case "event":
            return type + ": " + description + " (from: " + from.format(printPattern) +
                    " to: " + to.format(printPattern) + ")";
        default:
            return "";
        }
    }

}

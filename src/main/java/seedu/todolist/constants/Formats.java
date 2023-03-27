package seedu.todolist.constants;

/**
 * Enum that holds all the expected formatting strings for certain parameters.
 */
public enum Formats {
    // Format for task string: id, isDone, description, deadline
    TASK_STRING("[ID:%d]\t[%s][%s][Due: %s]"),
    TASK_STRING_NO_DEADLINE("[ID:%d]\t[%s][%s]"),
    TIME_IN("[d-M-uuuu H:m][d/M/uuuu H:m]"),
    TIME_OUT("dd LLL uuuu HH:mm");

    private final String format;

    Formats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}

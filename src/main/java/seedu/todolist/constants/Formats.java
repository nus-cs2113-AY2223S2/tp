package seedu.todolist.constants;

/**
 * Enum that holds all the expected formatting strings for certain parameters.
 */
public final class Formats {
    private Formats() {
    }

    public static final String TASK_STRING = "[ID:%06d][%s][%-38s]";
    public static final String TASK_STRING_INDEXED = "[#%06d]%s";
    public static final String TIME_IN_1 = "[d-M-uuuu H:m]";
    public static final String TIME_IN_2 = "[d/M/uuuu H:m]";
    public static final String TIME_OUT = "dd LLL uuuu HH:mm";
}

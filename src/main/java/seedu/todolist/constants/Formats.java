package seedu.todolist.constants;

public final class Formats {
    public static final String TASK_STRING = "[ID:%06d][%s][%-38s]";
    public static final String TASK_STRING_INDEXED = "[#%06d]%s";
    public static final String TIME_IN_1 = "[d-M-uuuu H:m]";
    public static final String TIME_IN_2 = "[d/M/uuuu H:m]";
    public static final String TIME_OUT = "dd LLL uuuu HH:mm";
    public static final String CONFIG_INFO = "Repeating tasks every %d days\nChecking for repeating task every: " +
                                                   "%d minutes\nLast checked at: %s";

    private Formats() {
    }
}

package seedu.todolist.constants;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class Formats {
    public static final String TASK_STRING = "[ID:%05d][%s][%s][%-35s]";
    public static final String TASK_STRING_INDEXED = "[#%05d]%s";
    public static final String TIME_IN_1 = "[d-M-uuuu H:m]";
    public static final String TIME_IN_2 = "[d/M/uuuu H:m]";
    public static final String TIME_OUT_UI = "dd LLL uuuu HH:mm";
    public static final String TIME_OUT_SAVE = "dd/MM/uuuu HH:mm";
    public static final String CONFIG_STRING = "Repeating tasks every %d days" + System.lineSeparator()
        + "Checking for repeating task every: %d minutes";

    public static final DateTimeFormatter TIME_IN_FORMATTER = DateTimeFormatter
            .ofPattern(Formats.TIME_IN_1 + Formats.TIME_IN_2).withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter TIME_OUT_UI_FORMATTER = DateTimeFormatter.ofPattern(Formats.TIME_OUT_UI);
    public static final DateTimeFormatter TIME_OUT_SAVE_FORMATTER = DateTimeFormatter.ofPattern(Formats.TIME_OUT_SAVE);

    private Formats() {
    }
}

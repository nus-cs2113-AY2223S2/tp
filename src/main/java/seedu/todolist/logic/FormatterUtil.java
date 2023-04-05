package seedu.todolist.logic;

import seedu.todolist.constants.Formats;
import seedu.todolist.constants.Priority;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class FormatterUtil {
    private FormatterUtil() {
    }

    public static String getPriorityAsString(int priority) {
        switch (priority) {
        case 1:
            return Priority.LOW.toString();
        case 2:
            return Priority.MEDIUM.toString();
        case 3:
            return Priority.HIGH.toString();
        default:
            return Priority.NONE.toString();
        }
    }

    public static String getDeadlineAsString(LocalDateTime deadline) {
        return deadline.format(Formats.TIME_OUT_UI_FORMATTER);
    }

    public static String getTagsAsString(TreeSet<String> tags) {
        return String.join(", ", tags);
    }
}

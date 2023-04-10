package seedu.todolist.logic;

import seedu.todolist.constants.Formats;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Utility class for formatting deadlines and lists of tags as a string, for display purposes.
 */
public class FormatterUtil {
    private FormatterUtil() {
    }

    public static String getDeadlineAsString(LocalDateTime deadline) {
        return deadline.format(Formats.TIME_OUT_UI_FORMATTER);
    }

    public static String getTagsAsString(TreeSet<String> tags) {
        return String.join(", ", tags);
    }
}

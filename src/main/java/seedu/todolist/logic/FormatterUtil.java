package seedu.todolist.logic;

import seedu.todolist.constants.Formats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class FormatterUtil {
    private FormatterUtil() {
    }

    public static String getPriorityAsString(int priority) {
        switch (priority) {
        case 1:
            return "Low";
        case 2:
            return "Medium";
        case 3:
            return "High";
        default:
            return "Unexpected value";
        }
    }

    public static String getDeadlineAsString(LocalDateTime deadline) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_OUT);
        return deadline.format(outputFormatter);
    }

    public static String getTagsAsString(TreeSet<String> tags) {
        return String.join(", ", tags);
    }
}

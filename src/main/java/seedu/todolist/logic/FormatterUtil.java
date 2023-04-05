package seedu.todolist.logic;

import seedu.todolist.constants.Formats;
import seedu.todolist.constants.PriorityLevels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class FormatterUtil {
    private FormatterUtil() {
    }

    public static String getPriorityAsString(int priority) {
        switch (priority) {
        case 1:
            return PriorityLevels.LOW.getPriority();
        case 2:
            return PriorityLevels.MEDIUM.getPriority();
        case 3:
            return PriorityLevels.HIGH.getPriority();
        default:
            return PriorityLevels.UNEXPECTED.getPriority();
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

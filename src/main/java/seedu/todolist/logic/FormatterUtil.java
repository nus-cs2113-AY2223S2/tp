package seedu.todolist.logic;

import seedu.todolist.constants.Formats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class FormatterUtil {
    private FormatterUtil() {
    }

    public static String getDeadlineAsString(LocalDateTime deadline) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_OUT.getFormat());
        return deadline.format(outputFormatter);
    }

    public static String getTagsAsString(HashSet<String> tags) {
        return String.join(" ", tags);
    }
}

package seedu.todolist.logic;

import seedu.todolist.constants.Formats;
import seedu.todolist.exception.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * Utility class for parsing arguments that are in the form of strings.
 */
public class ParserUtil {
    private ParserUtil() {
    }

    /**
     * Parses the description string, which is not allowed to be empty.
     *
     * @param description The description string.
     * @return The description of the task.
     */
    public static String parseDescription(String description) {
        return description;
    }

    /**
     * Parses the deadline string into a LocalDateTime object.
     * The deadline is allowed to be null as it is an optional parameter.
     *
     * @param deadline The deadline string.
     * @return A LocalDateTime object that contains the date and time in the string,
     *         if it was not null, null otherwise.
     * @throws InvalidDateException If the string is not in a valid date time format,
     *                              or if the parsed date is before the current time.
     */
    //@@author clement559
    public static LocalDateTime parseDeadline(String deadline) throws InvalidDateException {
        if (deadline == null) {
            return null;
        }

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_IN.getFormat())
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime dateTime = LocalDateTime.parse(deadline, inputFormatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new InvalidDateException(deadline);
            }
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(deadline);
        }
    }

    /**
     * Parses the email string, checking that is a valid email address.
     * The email is allowed to be null as it is an optional parameter.
     *
     * @param email The email address string.
     * @return The email address, if it was not null, null otherwise.
     * @throws InvalidEmailFormatException If the email address is invalid.
     */
    //@@author RuiShengGit
    public static String parseEmail(String email) throws InvalidEmailFormatException {
        if (email == null) {
            return null;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(email).matches()) {
            throw new InvalidEmailFormatException(email);
        }
        return email;
    }

    //@@author ERJUNZE
    /**
     * Parses the string of multiple tags into a hashset of tag strings.
     * Tags are separated from each other by a space.
     *
     * @param tags The string containing multiple tags.
     * @return A hashset containing the extracted tags.
     */
    public static HashSet<String> parseTags(String tags) {
        if (tags == null) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(tags.split(" ")));
    }

    /**
     * Parses the repeat duration of the task, which is in number of weeks.
     * A repeat duration can be specified only if deadline is not null.
     * If the repeat duration is null because it is not provided, it will default to 0.
     *
     * @param repeatDuration The repeat duration string.
     * @param deadline The deadline of the task.
     * @return The repeat duration of the task as an integer, if the deadline has been provided.
     * @throws InvalidDateException If the deadline is null.
     * @throws InvalidDurationException If the repeat duration cannot be parsed to an integer.
     */
    public static int parseRepeatDuration(String repeatDuration, LocalDateTime deadline)
            throws InvalidDateException, InvalidDurationException {
        if (repeatDuration == null) {
            return 0;
        }

        if (deadline == null) {
            throw new InvalidDateException("A deadline must be provided to use the repeat option.");
        }

        try {
            return Integer.parseInt(repeatDuration);
        } catch (NumberFormatException e) {
            throw new InvalidDurationException(repeatDuration);
        }
    }

    /**
     * Parses the index string.
     *
     * @param index The index string.
     * @return The index, as an integer.
     * @throws InvalidIndexException If the index cannot be parsed to an integer.
     */
    public static int parseIndex(String index) throws InvalidIndexException {
        try {
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(index);
        }
    }
}

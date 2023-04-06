package seedu.todolist.logic;

import seedu.todolist.constants.Formats;
import seedu.todolist.constants.Priority;
import seedu.todolist.exception.InvalidDateException;
import seedu.todolist.exception.InvalidDurationException;
import seedu.todolist.exception.InvalidEmailFormatException;
import seedu.todolist.exception.InvalidFrequencyException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidPriorityException;
import seedu.todolist.exception.PassedDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Utility class for parsing arguments that are in the form of strings.
 */
public class ParserUtil {
    private ParserUtil() {
    }

    //@@author jeromeongithub
    /**
     * Parses the id string into an integer. Does not check if id is in task list.
     *
     * @param idList The string containing the list of ids.
     * @return The ids, as a HashSet of integers.
     * @throws InvalidIdException If any id cannot be parsed to an integer.
     */
    public static HashSet<Integer> parseId(String idList) throws InvalidIdException {
        HashSet<Integer> idHashSet = new HashSet<>();
        for (String idString : idList.split(" ")) {
            try {
                int id = Integer.parseInt(idString);
                assert id >= 0 : "Parser should catch any negative values";
                idHashSet.add(id);
            } catch (NumberFormatException e) {
                throw new InvalidIdException(idString);
            }
        }
        return idHashSet;
    }

    //@@author RuiShengGit
    /**
     * Parses the priority string into an integer from 1 to 3, if it exists.
     * If the priority is null because it is not provided, it will default to 1.
     *
     * @param priorityString The priority string.
     * @return The priority, as an integer.
     * @throws InvalidPriorityException If the priority cannot be parsed to an integer, or if it is not from 1 to 3.
     */
    public static Priority parsePriority(String priorityString) throws InvalidPriorityException {
        if (priorityString == null) {
            return Priority.NONE;
        }

        try {
            int priority = Integer.parseInt(priorityString);
            switch (priority) {
            case 1:
                return Priority.LOW;
            case 2:
                return Priority.MEDIUM;
            case 3:
                return Priority.HIGH;
            default:
                throw new InvalidPriorityException(priorityString);
            }
        } catch (NumberFormatException e) {
            throw new InvalidPriorityException(priorityString);
        }
    }

    //@@author clement559
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
    public static LocalDateTime parseDeadline(String deadline) throws InvalidDateException, PassedDateException {
        if (deadline == null) {
            return null;
        }

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_IN_1 + Formats.TIME_IN_2)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime date = LocalDateTime.parse(deadline, inputFormatter);

            if (!date.isAfter(LocalDateTime.now())) {
                throw new PassedDateException(deadline);
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(deadline);
        }
    }

    //@@author RuiShengGit
    /**
     * Parses the email string, checking that is a valid email address.
     * The email is allowed to be null as it is an optional parameter.
     *
     * @param email The email address string.
     * @return The email address, if it was not null, null otherwise.
     * @throws InvalidEmailFormatException If the email address is invalid.
     */
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
    public static TreeSet<String> parseTags(String tags) {
        if (tags == null) {
            return new TreeSet<>();
        }
        return new TreeSet<>(Arrays.asList(tags.split(" ")));
    }

    //@@author clement559
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
     * Parses the frequency to be set in the config.
     *
     * @param frequency The frequency, as a string.
     * @return The frequency, as an integer, if it was not null, null otherwise.
     * @throws InvalidFrequencyException If the priority cannot be parsed to an integer.
     */
    public static int parseFrequency(String frequency) throws InvalidFrequencyException {
        if (frequency == null) {
            return -1;
        }

        try {
            assert Integer.parseInt(frequency) >= 0 : "Parser should catch any negative values";
            return Integer.parseInt(frequency);
        } catch (NumberFormatException e) {
            throw new InvalidFrequencyException(frequency);
        }
    }
}

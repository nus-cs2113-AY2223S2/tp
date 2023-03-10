package seedu.duke.command;

import seedu.duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

/**
 * Class containing a method for parsing the commands.
 */
public class CommandParser {
    /**
     * Splits the initial command string into its arguments.
     *
     * @param splitInput The command string, split into words.
     * @param flags Map of flags of the arguments that the command string are expected to contain.
     * @return Map of flags to arguments extracted from the command string.
     */
    public static HashMap<String, String> getArguments(String[] splitInput, HashSet<String> flags) {
        HashMap<String, String> arguments = new HashMap<>();
        StringJoiner currentArgument = new StringJoiner(" ");
        String currentFlag = splitInput[0];

        for (int i = 1; i < splitInput.length; i++) {
            // Append to current argument if current word is not a new flag.
            if (!flags.contains(splitInput[i])) {
                currentArgument.add(splitInput[i]);
                continue;
            }

            // Repeating flags are not allowed.
            if (arguments.containsKey(splitInput[i]) || currentFlag.equals(splitInput[i])) {
                // Unimplemented exception, break as placeholder
                break;
            }

            // Add current flag-argument combo to argument list if current word is a new flag.
            arguments.put(currentFlag, currentArgument.toString());
            currentArgument = new StringJoiner(" ");
            currentFlag = splitInput[i];
        }
        // Add last flag-argument combo to argument list.
        arguments.put(currentFlag, currentArgument.toString());

        return arguments;
    }

    // Adapted from Clement559 iP
    public static String formatDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatterInput = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        DateTimeFormatter dateTimeFormatterOutput = DateTimeFormatter.ofPattern("LLL dd uuuu HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatterInput);
        String formattedDateTime = dateTime.format(dateTimeFormatterOutput);
        return formattedDateTime;
    }

    /**
     * Parses the given command string into a command that can be executed.
     *
     * @param input The unparsed command string.
     * @return A command that can be executed by calling the run() method.
     */
    public static Command parseCommand(String input, TaskList taskList) {
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");
        switch (splitInput[0]) {
        case AddTaskCommand.KEYWORD:
            return checkValidityAdd(splitInput);
        case ListTasksCommand.KEYWORD:
            return new ListTasksCommand();
        case MarkTaskCommand.KEYWORD:
            return new MarkTaskCommand(splitInput);
        case UnmarkTaskCommand.KEYWORD:
            return new UnmarkTaskCommand(splitInput);
        case EditDeadlineCommand.KEYWORD:
            return checkValidityEdit(splitInput);
        case DeleteCommand.KEYWORD:
            return new DeleteCommand(splitInput,taskList);
        case ExitCommand.KEYWORD:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    public static Command checkValidityAdd (String[] splitInput) {
        try {
            return new AddTaskCommand(splitInput);
        } catch (NullPointerException | DateTimeParseException e) {
            return new InvalidCommand();
        }
    }
    public static Command checkValidityEdit (String[] splitInput) {
        try {
            return new EditDeadlineCommand(splitInput);
        } catch (DateTimeParseException | NumberFormatException e) {
            return new InvalidCommand();
        }
    }
}

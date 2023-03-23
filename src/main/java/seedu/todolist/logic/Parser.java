package seedu.todolist.logic;

import seedu.todolist.constants.Formats;
import seedu.todolist.exception.InvalidCommandException;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.InvalidTimeException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.command.AddTaskCommand;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.DeleteTaskCommand;
import seedu.todolist.logic.command.EditDeadlineCommand;
import seedu.todolist.logic.command.ExitCommand;
import seedu.todolist.logic.command.ListTasksCommand;
import seedu.todolist.logic.command.MarkTaskCommand;
import seedu.todolist.logic.command.UnmarkTaskCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

/**
 * Class containing a method for parsing the commands.
 */
public class Parser {
    /**
     * Splits the initial command string into its arguments.
     *
     * @param splitInput The command string, split into words.
     * @param flags Map of flags of the arguments that the command string are expected to contain.
     * @return Map of flags to arguments extracted from the command string.
     * @throws InvalidFlagException if there are multiple of the same flag, which is not allowed.
     */
    public HashMap<String, String> getArguments(String[] splitInput, HashSet<String> flags)
            throws InvalidFlagException {
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
                throw new InvalidFlagException();
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

    //@@author clement559
    public static LocalDateTime formatDateTime(String date) throws InvalidTimeException {
        if (date == null) {
            throw new InvalidTimeException();
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_IN.FORMAT)
                .withResolverStyle(ResolverStyle.STRICT);
        return LocalDateTime.parse(date, inputFormatter);
    }

    //@@author ERJUNZE
    /**
     * Parses the given command string into a command that can be executed.
     *
     * @param input The unparsed command string.
     * @return A command that can be executed by calling the run() method.
     * @throws ToDoListException if there is an error in parsing the command.
     */
    public Command parseCommand(String input) throws ToDoListException {
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");
        switch (splitInput[0]) {
        case AddTaskCommand.KEYWORD:
            return new AddTaskCommand(getArguments(splitInput, AddTaskCommand.FLAGS));
        case ListTasksCommand.KEYWORD:
            return new ListTasksCommand();
        case MarkTaskCommand.KEYWORD:
            return new MarkTaskCommand(getArguments(splitInput, MarkTaskCommand.FLAGS));
        case UnmarkTaskCommand.KEYWORD:
            return new UnmarkTaskCommand(getArguments(splitInput, UnmarkTaskCommand.FLAGS));
        case EditDeadlineCommand.KEYWORD:
            return new EditDeadlineCommand(getArguments(splitInput, EditDeadlineCommand.FLAGS));
        case DeleteTaskCommand.KEYWORD:
            return new DeleteTaskCommand(getArguments(splitInput, DeleteTaskCommand.FLAGS));
        case ExitCommand.KEYWORD:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }
}

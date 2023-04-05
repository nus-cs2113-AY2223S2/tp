package seedu.todolist.logic;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidCommandException;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.MissingArgumentException;
import seedu.todolist.exception.ToDoListException;

import seedu.todolist.logic.command.AddTaskCommand;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.DeleteTaskCommand;
import seedu.todolist.logic.command.EditDeadlineCommand;
import seedu.todolist.logic.command.EditDescriptionCommand;
import seedu.todolist.logic.command.EditEmailCommand;
import seedu.todolist.logic.command.EditPriorityCommand;
import seedu.todolist.logic.command.EditRepeatCommand;
import seedu.todolist.logic.command.EditTagsCommand;
import seedu.todolist.logic.command.ExitCommand;
import seedu.todolist.logic.command.FindByPriorityCommand;
import seedu.todolist.logic.command.FindByTagCommand;
import seedu.todolist.logic.command.ListFullInfoCommand;
import seedu.todolist.logic.command.ListTagsCommand;
import seedu.todolist.logic.command.ListTasksCommand;
import seedu.todolist.logic.command.MarkTaskCommand;
import seedu.todolist.logic.command.ProgressBarCommand;
import seedu.todolist.logic.command.UnmarkTaskCommand;
import seedu.todolist.logic.command.HelpCommand;
import seedu.todolist.logic.command.EditConfigCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

/**
 * Class containing a method for parsing the commands.
 */
public class Parser {
    private Flags parseFlag(String word, HashSet<Flags> allowedFlagsSet, Flags currentFlag,
                            HashMap<Flags, String> arguments) throws InvalidFlagException {
        Flags flag = Flags.fromString(word);
        boolean isUnexpectedFlag = flag == null || !allowedFlagsSet.contains(flag);
        boolean isRepeatedFlag = currentFlag.equals(flag) || arguments.containsKey(flag);
        if (isUnexpectedFlag || isRepeatedFlag) {
            throw new InvalidFlagException(word);
        }
        return flag;
    }

    private String getArgumentString(Flags flag, StringJoiner argument) throws MissingArgumentException {
        if (argument.length() == 0 && !flag.canBeEmpty()) {
            // Empty argument not allowed for this flag
            throw new MissingArgumentException(flag);
        }
        return argument.toString();
    }

    /**
     * Splits the initial command string into its arguments.
     *
     * @param splitInput The command string, split into words.
     * @param allowedFlags Array of flags that are allowed for this command.
     * @return Map of flags to arguments extracted from the command string.
     * @throws InvalidFlagException If there are multiple of the same flag or unexpected flags for this command.
     * @throws MissingArgumentException If arguments are not provided for flags that need them.
     */
    private HashMap<Flags, String> getArguments(String[] splitInput, Flags[] allowedFlags)
            throws InvalidFlagException, MissingArgumentException {
        HashMap<Flags, String> arguments = new HashMap<>();
        StringJoiner currentArgument = new StringJoiner(" ");
        Flags currentFlag = allowedFlags[0];
        HashSet<Flags> allowedFlagsSet = new HashSet<>(Arrays.asList(allowedFlags));

        for (String word : Arrays.copyOfRange(splitInput, 1, splitInput.length)) {
            // Append to current argument if current word is not a new flag.
            if (!word.startsWith("-")) {
                currentArgument.add(word);
                continue;
            }

            // Current word is a new flag, check if it is valid
            Flags flag = parseFlag(word, allowedFlagsSet, currentFlag, arguments);
            // Add current flag-argument combo to argument list and reset the argument string.
            arguments.put(currentFlag, getArgumentString(currentFlag, currentArgument));
            currentArgument = new StringJoiner(" ");
            currentFlag = flag;
        }
        // Add last flag-argument combo to argument list.
        arguments.put(currentFlag, getArgumentString(currentFlag, currentArgument));

        return arguments;
    }

    /**
     * Parses the given command string into a command that can be executed.
     *
     * @param input The unparsed command string.
     * @return A command that can be executed by calling the run() method.
     * @throws ToDoListException If there is an error in parsing the command.
     */
    public Command parseCommand(String input) throws ToDoListException {
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");
        Flags command = Flags.fromString(splitInput[0]);

        if (command == null) {
            // Command not found
            throw new InvalidCommandException();
        }
        switch (command) {
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListTasksCommand(getArguments(splitInput, ListTasksCommand.EXPECTED_FLAGS));
        case COMMAND_TAG_LIST:
            return new ListTagsCommand();
        case COMMAND_PROGRESS:
            return new ProgressBarCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_ADD:
            return new AddTaskCommand(getArguments(splitInput, AddTaskCommand.EXPECTED_FLAGS));
        case COMMAND_MARK:
            return new MarkTaskCommand(getArguments(splitInput, MarkTaskCommand.EXPECTED_FLAGS));
        case COMMAND_UNMARK:
            return new UnmarkTaskCommand(getArguments(splitInput, UnmarkTaskCommand.EXPECTED_FLAGS));
        case COMMAND_DELETE:
            return new DeleteTaskCommand(getArguments(splitInput, DeleteTaskCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_DESCRIPTION:
            return new EditDescriptionCommand(getArguments(splitInput, EditDescriptionCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_DEADLINE:
            return new EditDeadlineCommand(getArguments(splitInput, EditDeadlineCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_EMAIL:
            return new EditEmailCommand(getArguments(splitInput, EditEmailCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_TAGS:
            return new EditTagsCommand(getArguments(splitInput, EditTagsCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_REPEAT:
            return new EditRepeatCommand(getArguments(splitInput, EditRepeatCommand.EXPECTED_FLAGS));
        case COMMAND_EDIT_PRIORITY:
            return new EditPriorityCommand(getArguments(splitInput, EditPriorityCommand.EXPECTED_FLAGS));
        case COMMAND_FULL_INFO:
            return new ListFullInfoCommand(getArguments(splitInput, ListFullInfoCommand.EXPECTED_FLAGS));
        case COMMAND_FIND_TAG:
            return new FindByTagCommand(getArguments(splitInput, FindByTagCommand.EXPECTED_FLAGS));
        case COMMAND_FIND_PRIORITY:
            return new FindByPriorityCommand(getArguments(splitInput, FindByPriorityCommand.EXPECTED_FLAGS));
        case COMMAND_CONFIG:
            return new EditConfigCommand(getArguments(splitInput, EditConfigCommand.EXPECTED_FLAGS));
        default:
            throw new InvalidCommandException();
        }
    }
}

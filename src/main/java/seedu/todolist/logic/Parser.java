package seedu.todolist.logic;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidCommandException;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.MissingArgumentException;
import seedu.todolist.exception.ToDoListException;

import seedu.todolist.logic.command.AddTaskCommand;
import seedu.todolist.logic.command.CheckRepeatingTaskCommand;
import seedu.todolist.logic.command.Command;
import seedu.todolist.logic.command.DeleteTaskCommand;
import seedu.todolist.logic.command.EditDeadlineCommand;
import seedu.todolist.logic.command.EditDescriptionCommand;
import seedu.todolist.logic.command.EditEmailCommand;
import seedu.todolist.logic.command.EditRepeatCommand;
import seedu.todolist.logic.command.ExitCommand;
import seedu.todolist.logic.command.ListFullInfoCommand;
import seedu.todolist.logic.command.ListTagsCommand;
import seedu.todolist.logic.command.ListTasksCommand;
import seedu.todolist.logic.command.MarkTaskCommand;
import seedu.todolist.logic.command.ProgressBarCommand;
import seedu.todolist.logic.command.EditTagsCommand;
import seedu.todolist.logic.command.UnmarkTaskCommand;
import seedu.todolist.logic.command.EditPriorityCommand;

import java.util.Arrays;
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
     * @throws InvalidFlagException If there are multiple of the same flag or unexpected flags for this command.
     * @throws MissingArgumentException If arguments are not provided for flags that need them.
     */
    public HashMap<Flags, String> getArguments(String[] splitInput, Flags[] flags)
            throws InvalidFlagException, MissingArgumentException {
        HashMap<Flags, String> arguments = new HashMap<>();
        StringJoiner currentArgument = new StringJoiner(" ");
        Flags currentFlag = flags[0];
        HashSet<Flags> flagsSet = new HashSet<>(Arrays.asList(flags));

        for (String word : Arrays.copyOfRange(splitInput, 1, splitInput.length)) {
            // Append to current argument if current word is not a new flag.
            if (!word.startsWith("-")) {
                currentArgument.add(word);
                continue;
            }

            // Current word is a new flag
            Flags flag = Flags.fromString(word);
            // Unexpected and duplicate flags for this command are not allowed
            boolean unexpectedFlag = flag == null || !flagsSet.contains(flag);
            boolean duplicateFlag = currentFlag.equals(flag) || arguments.containsKey(flag);
            if (unexpectedFlag || duplicateFlag) {
                throw new InvalidFlagException(word);
            }

            // Add current flag-argument combo to argument list and reset the argument string.
            if (currentArgument.length() == 0 && !currentFlag.canBeEmpty()) {
                // Empty argument not allowed for this flag
                throw new MissingArgumentException(currentFlag);
            }
            arguments.put(currentFlag, currentArgument.toString());
            currentArgument = new StringJoiner(" ");
            currentFlag = flag;
        }
        // Add last flag-argument combo to argument list.
        if (currentArgument.length() == 0 && !currentFlag.canBeEmpty()) {
            throw new MissingArgumentException(currentFlag);
        }
        arguments.put(currentFlag, currentArgument.toString());

        return arguments;
    }

    //@@author ERJUNZE
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
            return new ListTasksCommand();
        case COMMAND_TAG_LIST:
            return new ListTagsCommand();
        case COMMAND_PROGRESS:
            return new ProgressBarCommand();
        case COMMAND_CHECK:
            return new CheckRepeatingTaskCommand();
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
        default:
            throw new InvalidCommandException();
        }
    }
}

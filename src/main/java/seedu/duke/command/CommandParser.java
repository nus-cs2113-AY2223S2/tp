package seedu.duke.command;

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

        for (String word : splitInput) {
            // Append to current argument if current word is not a new flag.
            if (!flags.contains(word)) {
                currentArgument.add(word);
                continue;
            }

            // Repeating flags are not allowed.
            if (arguments.containsKey(word) || currentFlag.equals(word)) {
                // Unimplemented exception, break as placeholder
                break;
            }

            // Add current flag-argument combo to argument list if current word is a new flag.
            arguments.put(currentFlag, currentArgument.toString());
            currentArgument = new StringJoiner(" ");
            currentFlag = word;
        }
        // Add last flag-argument combo to argument list.
        arguments.put(currentFlag, currentArgument.toString());

        return arguments;
    }

    /**
     * Parses the given command string into a command that can be executed.
     *
     * @param input The unparsed command string.
     * @return A command that can be executed by calling the run() method.
     */
    public Command parseCommand(String input) {
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");

        switch (splitInput[0]) {
        case ListTasksCommand.KEYWORD:
            return new ListTasksCommand();
        case ExitCommand.KEYWORD:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}

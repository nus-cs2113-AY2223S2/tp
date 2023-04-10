package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;

public class ViewerHelpCommand extends Command {
    private static final String HELP = "Here are the list of valid commands: \n"
            + String.format("%53s", "_".repeat(53))
            + String.format("%n|%-25s|%-25s|", " Command Type", " Command Usage")
            + String.format("%n|%-25s|%-25s|", "-".repeat(25), "-".repeat(25))
            + String.format("%n|%-25s|%-25s|", " Add new PC", " add PC_NAME")
            + String.format("%n|%-25s|%-25s|", " Edit PC", " edit PC_INDEX")
            + String.format("%n|%-25s|%-25s|", " View PC Specs", " view PC_INDEX")
            + String.format("%n|%-25s|%-25s|", " Delete PC", " delete PC_INDEX")
            + String.format("%n|%-25s|%-25s|", " Filter PC List", " filter FILTER_FLAGS")
            + String.format("%n|%-25s|%-25s|", " Exit program", " bye")
            + String.format("%n%53s", "-".repeat(53))
            + "\nFor more detailed documentation on commands, please refer to our user guide!\n";

    /**
     * Help command to view the list of valid commands
     * @return the list of valid commands
     */
    @Override
    public String executeCommand(DataStorage dataStorage) {
        return getHelp();
    }

    private static String getHelp() {
        return HELP;
    }
}

package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;

public class BuilderHelpCommand extends Command {
    private static final String HELP = "Here are the list of valid commands: \n"
            + String.format("%78s", "_".repeat(78))
            + String.format("%n|%-25s|%-50s|", " Command Type", " Command Usage")
            + String.format("%n|%-25s|%-50s|", "-".repeat(25), "-".repeat(50))
            + String.format("%n|%-25s|%-50s|", " List Component", " list COMPONENT_TYPE [-COMPONENT_FLAG lIST_FLAG]")
            + String.format("%n|%-25s|%-50s|", " Select Component", " select COMPONENT_TYPE COMPONENT_INDEX")
            + String.format("%n|%-25s|%-50s|", " Unselect Component", " unselect COMPONENT_TYPE")
            + String.format("%n|%-25s|%-50s|", " Compare Components", " compare COMPONENT_TYPE INDEX_1 & INDEX_2")
            + String.format("%n|%-25s|%-50s|", " Change Budget", " budget POSITIVE_INTEGER")
            + String.format("%n|%-25s|%-50s|", " Change PC Name", " name PC_NAME")
            + String.format("%n|%-25s|%-50s|", " Create Custom Component", " custom COMPONENT_TYPE SPEC1|SPEC_2|...")
            + String.format("%n|%-25s|%-50s|", " Back Command", " back")
            + String.format("%n|%-25s|%-50s|", " Exit program", " bye")
            + String.format("%n%78s", "-".repeat(78))
            + "\nFor more detailed documentation on commands, please refer to our user guide!\n";

    /**
     * Help command to view the list of valid commands when in Builder mode
     * @return the list of valid commands
     */
    @Override
    public String executeCommand(DataStorage dataStorage) {
        return getHelp();
    }

    public static String getHelp() {
        return HELP;
    }
}


package seedu.bigpp.command.viewercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {

    @Test
    public void executeCommand_deleteIndexCommand_success() {
        DataStorage dataStorage = new DataStorage();

        String expectedOutput = "Here are the list of valid commands: \n"
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

        ViewerHelpCommand command = new ViewerHelpCommand();
        String output = command.executeCommand(dataStorage);

        assertEquals(expectedOutput, output);
    }
}

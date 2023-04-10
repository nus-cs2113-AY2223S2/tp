package seedu.bigpp.command.commoncommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.bigpp.datastorage.DataStorage;

public class ByeCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_byeCommand_success() {
        ByeCommand byeCommand = new ByeCommand();
        String expectedOutput = "Bye. Hope to see you again soon!";
        String actualOutput = byeCommand.executeCommand(dataStorage);
        assertEquals(expectedOutput, actualOutput);
    }
}

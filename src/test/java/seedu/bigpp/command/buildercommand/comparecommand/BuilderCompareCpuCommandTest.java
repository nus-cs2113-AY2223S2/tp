package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareCpuCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validCpuIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareCpuCommand command = new BuilderCompareCpuCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "Intel core i3-10100", "Intel core i5-10600k") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$99.5", "$182.47") +
                String.format("%n|%-12s|%-40s|%-40s|", "SOCKET", "LGA1200", "LGA1200") +
                String.format("%n|%-12s|%-40s|%-40s|", "CORES", "4", "6") +
                String.format("%n|%-12s|%-40s|%-40s|", "THREADS", "8", "12") +
                String.format("%n|%-12s|%-40s|%-40s|", "BASE CLOCK", "3.6GHz", "4.1GHz") +
                String.format("%n|%-12s|%-40s|%-40s|", "BOOST CLOCK", "4.3GHz", "4.8GHz") +
                String.format("%n|%-12s|%-40s|%-40s|", "POWER", "65.0W", "125.0W") +
                String.format("%n%96s", "_".repeat(96));
        assertEquals(expectedOutput, output);
    }

    @Test
    public void executeCommand_invalidArgumentFormat_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 2";
        BuilderCompareCpuCommand command = new BuilderCompareCpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareCpuCommand command = new BuilderCompareCpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareCpuCommand command = new BuilderCompareCpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}

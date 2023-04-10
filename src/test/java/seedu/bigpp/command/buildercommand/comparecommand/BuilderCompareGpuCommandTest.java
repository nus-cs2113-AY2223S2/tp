package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareGpuCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validGpuIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareGpuCommand command = new BuilderCompareGpuCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "Gigabyte GAMING OC RTX3050",
                        "MSI GeForce RTX 3060 Gaming X12G") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$309.99", "$379.99") +
                String.format("%n|%-12s|%-40s|%-40s|", "POWER", "115.0W", "170.0W") +
                String.format("%n|%-12s|%-40s|%-40s|", "FORM FACTOR", "mini", "micro") +
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
        BuilderCompareGpuCommand command = new BuilderCompareGpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareGpuCommand command = new BuilderCompareGpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareGpuCommand command = new BuilderCompareGpuCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}


package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareStorageCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validStorageIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareStorageCommand command = new BuilderCompareStorageCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "ADATA XPG SPECTRIX S40G RGB", "Seagate BarraCuda") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$54.99", "$95.0") +
                String.format("%n|%-12s|%-40s|%-40s|", "TYPE", "M.2 SSD", "HDD") +
                String.format("%n|%-12s|%-40s|%-40s|", "SIZE", "512GB", "1024GB") +
                String.format("%n|%-12s|%-40s|%-40s|", "POWER", "4.5W", "6.8W") +
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
        BuilderCompareStorageCommand command = new BuilderCompareStorageCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareStorageCommand command = new BuilderCompareStorageCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareStorageCommand command = new BuilderCompareStorageCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}

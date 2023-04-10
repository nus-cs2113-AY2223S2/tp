package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareRamCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validRamIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareRamCommand command = new BuilderCompareRamCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "Kingston FURY Beast 16 GB", "G.Skill Ripjaws X 8 GB") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$43.98", "$22.99") +
                String.format("%n|%-12s|%-40s|%-40s|", "MEMORY", "8GB", "8GB") +
                String.format("%n|%-12s|%-40s|%-40s|", "STICKS", "2", "1") +
                String.format("%n|%-12s|%-40s|%-40s|", "SPEED", "3200MHz", "1600MHz") +
                String.format("%n|%-12s|%-40s|%-40s|", "POWER", "5.0W", "3.0W") +
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
        BuilderCompareRamCommand command = new BuilderCompareRamCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareRamCommand command = new BuilderCompareRamCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareRamCommand command = new BuilderCompareRamCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}


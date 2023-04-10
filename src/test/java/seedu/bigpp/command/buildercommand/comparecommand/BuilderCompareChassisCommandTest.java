package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareChassisCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validChassisIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareChassisCommand command = new BuilderCompareChassisCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "Fractal Design Pop XL Air", "Corsair iCUE 4000X RGB") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "BRAND", "Fractal Design", "Corsair") +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$124.98", "$144.99") +
                String.format("%n|%-12s|%-40s|%-40s|", "FORM FACTOR", "atx", "atx") +
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
        BuilderCompareChassisCommand command = new BuilderCompareChassisCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareChassisCommand command = new BuilderCompareChassisCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareChassisCommand command = new BuilderCompareChassisCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}

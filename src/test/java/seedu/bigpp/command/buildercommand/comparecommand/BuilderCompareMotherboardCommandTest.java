package seedu.bigpp.command.buildercommand.comparecommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderCompareMotherboardCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_validMotherboardIndexes_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);
        // Create test command and execute
        String arguments = "1 & 2";
        BuilderCompareMotherboardCommand command = new BuilderCompareMotherboardCommand(arguments);
        String output = command.executeCommand(dataStorage);

        // Check expected output
        String expectedOutput = String.format("%96s", "_".repeat(96)) +
                String.format("%n|%-12s|%-40s|%-40s|", "NAME", "Asus Prime Z590-P WiFi",
                        "MSI MAG Z690 TOMAHAWK WIFI DDR4") +
                String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40)) +
                String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$121.99", "$239.99") +
                String.format("%n|%-12s|%-40s|%-40s|", "SOCKET", "LGA1200", "LGA1700") +
                String.format("%n|%-12s|%-40s|%-40s|", "FORM FACTOR", "atx", "atx") +
                String.format("%n|%-12s|%-40s|%-40s|", "POWER", "45.0W", "47.0W") +
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
        BuilderCompareMotherboardCommand command = new BuilderCompareMotherboardCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_invalidComponentIndex_exceptionThrown() {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & 500";
        BuilderCompareMotherboardCommand command = new BuilderCompareMotherboardCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }

    @Test
    public void executeCommand_nonNumericComponentIndex_exceptionThrown() {
        // Create test data
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        // Create test command and execute
        String arguments = "1 & abc";
        BuilderCompareMotherboardCommand command = new BuilderCompareMotherboardCommand(arguments);
        assertThrows(PPException.class, () -> command.executeCommand(dataStorage));
    }
}

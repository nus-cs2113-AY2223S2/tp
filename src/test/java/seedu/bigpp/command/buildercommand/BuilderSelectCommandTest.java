package seedu.bigpp.command.buildercommand;

import seedu.bigpp.datastorage.DataStorage;
import org.junit.jupiter.api.Test;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderSelectCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void excecuteCommand_selectCommand_success() throws PPException {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        String selectCommandResult = new BuilderSelectCommand("cpu 1").executeCommand(dataStorage);
        assertEquals("cpu added! : Intel core i3-10100", selectCommandResult);
    }

    @Test
    public void excecuteCommand_selectCommandEmpty_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderSelectCommand("").executeCommand(
                dataStorage));
        assertEquals(
                "Please select a component",
                exception.getMessage());
    }

    @Test
    public void executeCommand_selectInvalidComponent_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderSelectCommand("component")
                .executeCommand(
                        dataStorage));
        assertEquals("Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)",
                exception.getMessage());
    }

    @Test
    public void executeCommand_missingIndexOfComponent_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderSelectCommand("cpu")
                .executeCommand(
                        dataStorage));
        assertEquals("Please input an index after selecting a component",
                exception.getMessage());
    }

    @Test
    public void executeCommand_invalidTypeForIndexOfComponent_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderSelectCommand("cpu a")
                .executeCommand(
                        dataStorage));
        assertEquals("Please enter a positive integer",
                exception.getMessage());
    }

    @Test 
    public void executeCommand_outOFBoundsIndex_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderSelectCommand("cpu 100")
                .executeCommand(
                        dataStorage));
        assertEquals("Please enter a valid index",
                exception.getMessage());
    }
}

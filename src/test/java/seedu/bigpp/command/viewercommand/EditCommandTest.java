package seedu.bigpp.command.viewercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.PC;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_editIndexCommand_success() throws PPException {
        DataStorage dataStorage = new DataStorage();
        dataStorage.pcList.add(new PC("Test PC", false));

        ViewerEditCommand command = new ViewerEditCommand("1");
        String result = command.executeCommand(dataStorage);
        UI.setPCBuilderMode(0);

        assertEquals("Currently editing PC: Test PC", result);
    }

    @Test
    public void  executeCommand_emptyIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerEditCommand("")
                .executeCommand(dataStorage));

        assertEquals("Please input an index", exception.getMessage());
    }

    @Test
    public void executeCommand_nonIntegerIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerEditCommand("abc")
                .executeCommand(dataStorage));

        assertEquals("Please enter a positive integer",  exception.getMessage());
    }

    @Test
    public void executeCommand_invalidIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerEditCommand("0")
                .executeCommand(dataStorage));

        assertEquals("Please enter a valid index", exception.getMessage());
    }
}

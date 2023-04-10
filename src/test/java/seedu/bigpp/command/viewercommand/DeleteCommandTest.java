package seedu.bigpp.command.viewercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.exceptions.PPException;

import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class DeleteCommandTest {
    @Test
    public void executeCommand_deleteIndexCommand_success() throws PPException {
        DataStorage dataStorage = new DataStorage();

        PC pc1 = new PC("PC1", false);
        PC pc2 = new PC("PC2", false);

        dataStorage.pcList.add(pc1);
        dataStorage.pcList.add(pc2);

        ViewerDeleteCommand command = new ViewerDeleteCommand("2");
        String result = command.executeCommand(dataStorage);

        assertEquals("Custom PC: [ PC2 ] has been deleted", result);
        assertEquals(1, dataStorage.pcList.size());
    }

    @Test
    public void  executeCommand_emptyIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerDeleteCommand("")
                .executeCommand(dataStorage));

        assertEquals("Please input an index", exception.getMessage());
    }

    @Test
    public void executeCommand_invalidIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerDeleteCommand("0")
                .executeCommand(dataStorage));

        assertEquals("Please enter a valid index", exception.getMessage());
    }

    @Test
    public void executeCommand_nonIntegerIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerDeleteCommand("abc")
                .executeCommand(dataStorage));

        assertEquals("Please enter a positive integer",  exception.getMessage());
    }

    @Test
    public void executeCommand_unableDeletePrebuiltPC_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        PC pc1 = new PC("PC1", false);
        PC pc2 = new PC("PC2", true);

        dataStorage.pcList.add(pc1);
        dataStorage.pcList.add(pc2);

        Exception exception = assertThrows(PPException.class, () -> new ViewerDeleteCommand("2")
                .executeCommand(dataStorage));

        assertEquals("Unable to delete Prebuilt PC", exception.getMessage());
    }
}

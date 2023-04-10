package seedu.bigpp.command.viewercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilterCommandTest {
    @Test
    public void executeCommand_filterPCListCommandByName_success() throws PPException {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();
        dataStorage.loadAll();

        ViewerFilterCommand command = new ViewerFilterCommand("-name intermediate");
        String result = command.executeCommand(dataStorage);
        assertEquals("Filter completed" , result);
    }

    @Test
    public void executeCommand_filterPCListCommandByPrice_success() throws PPException {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();
        dataStorage.loadAll();

        ViewerFilterCommand command = new ViewerFilterCommand("-price /from 1000 /to 2000");
        String result = command.executeCommand(dataStorage);

        assertEquals("Filter completed" , result);
    }

    @Test
    public void executeCommand_filterPCListCommandByIsBuilt_success() throws PPException {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();
        dataStorage.loadAll();

        ViewerFilterCommand command = new ViewerFilterCommand("-built complete");
        String result = command.executeCommand(dataStorage);

        assertEquals("Filter completed" , result);
    }

    @Test
    public void executeCommand_clearFilterCommand_success() throws PPException {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();
        dataStorage.loadAll();

        ViewerFilterCommand command = new ViewerFilterCommand("-clear");
        String result = command.executeCommand(dataStorage);

        assertEquals("Filter cleared" , result);
    }

    @Test
    public void executeCommand_invalidFlag_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("")
                .executeCommand(dataStorage));

        assertEquals("Please enter a valid flag", exception.getMessage());
    }

    @Test
    public void executeCommand_invalidFlagAndDescriptionIndex_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("abc")
                .executeCommand(dataStorage));

        assertEquals("Please enter a valid flag and description",  exception.getMessage());
    }

    @Test
    public void executeCommand_missingPriceDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price ")
                .executeCommand(dataStorage));

        assertEquals("Please enter a price description after the flag",  exception.getMessage());
    }

    @Test
    public void executeCommand_incompletePriceDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from 1000 /to ").executeCommand(dataStorage));

        assertEquals("Please enter the full price description after the flag containing the start "
                + "and end price range",  exception.getMessage());
    }

    @Test
    public void executeCommand_missingPriceFromDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "from 1000 /to 3000").executeCommand(dataStorage));

        assertEquals("Please use /from to specify the start price range",  exception.getMessage());
    }

    @Test
    public void executeCommand_missingPriceToDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from 1000 to 3000").executeCommand(dataStorage));

        assertEquals("Please use /to to specify the end price range",  exception.getMessage());
    }

    @Test
    public void executeCommand_invalidPriceFromDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from abc /to 3000").executeCommand(dataStorage));

        assertEquals("Start price must be a positive integer",  exception.getMessage());
    }

    @Test
    public void executeCommand_invalidPriceToDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from 1000 /to abc").executeCommand(dataStorage));

        assertEquals("End price must be a positive integer",  exception.getMessage());
    }

    @Test
    public void executeCommand_startPriceBiggerThanEndPrice_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from 3000 /to 1000").executeCommand(dataStorage));

        assertEquals("Start price must be less than end price",  exception.getMessage());
    }

    @Test
    public void executeCommand_priceIsTooLarge_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-price " +
                "/from 1000 /to 10000000").executeCommand(dataStorage));

        assertEquals("price must be smaller than 1000000",  exception.getMessage());
    }

    @Test
    public void executeCommand_missingBuiltDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-built ")
                .executeCommand(dataStorage));

        assertEquals("Please enter a built description after the flag",  exception.getMessage());
    }

    @Test
    public void executeCommand_builtDescriptionIsAFlag_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-built -built")
                .executeCommand(dataStorage));

        assertEquals("Flag detected as a built. Please enter another built after the flag",
                exception.getMessage());
    }

    @Test
    public void executeCommand_builtDescriptionIsInvalid_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-built abc")
                .executeCommand(dataStorage));

        assertEquals("built description is incorrect, please enter either complete or incomplete",
                exception.getMessage());
    }

    @Test
    public void executeCommand_missingNameDescription_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-name ")
                .executeCommand(dataStorage));

        assertEquals("Please enter a name after the flag",  exception.getMessage());
    }

    @Test
    public void executeCommand_nameDescriptionIsAFlag_exceptionThrown() {
        UI.setPCViewerMode();
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerFilterCommand("-name -name")
                .executeCommand(dataStorage));

        assertEquals("Flag detected as a name. Please enter another name after the flag",
                exception.getMessage());
    }
}

package seedu.bigpp.command.commoncommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.ui.UI;
import seedu.bigpp.datastorage.DataStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_backCommand_success() {
        BackCommand backCommand = new BackCommand();
        UI.setPCViewerMode();
        String pcViewerBackCommandResult = backCommand.executeCommand(dataStorage);
        assertEquals("Cannot go back from PCViewer", pcViewerBackCommandResult);

        //edits first pc in pclist
        UI.setPCBuilderMode(0);
        String pcBuilderBackCommandResult = backCommand.executeCommand(dataStorage);
        assertEquals("returned to PCViewer", pcBuilderBackCommandResult);
    }
}

package seedu.bigpp.command.commoncommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackCommandTest {

    @Test
    public void executeCommand_backCommand_success() {
        BackCommand backCommand = new BackCommand();
        UI.setPCViewerMode();
        String pcViewerBackCommandResult = backCommand.executeCommand();
        assertEquals("Cannot go back from PCViewer", pcViewerBackCommandResult);

        //edits first pc in pclist
        UI.setPCBuilderMode(0);
        String pcBuilderBackCommandResult = backCommand.executeCommand();
        assertEquals("returned to PCViewer", pcBuilderBackCommandResult);
    }
}

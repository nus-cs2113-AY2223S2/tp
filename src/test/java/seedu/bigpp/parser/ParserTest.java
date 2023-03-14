package seedu.bigpp.parser;

import org.junit.jupiter.api.Test;
import seedu.bigpp.command.Command;
import seedu.bigpp.command.commoncommand.BackCommand;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.viewercommand.ViewerAddCommand;
import seedu.bigpp.command.viewercommand.ViewerDeleteCommand;
import seedu.bigpp.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    @Test
    void parseCommand_backCommand_success() {
        Parser parser = new Parser();
        UI.setPCViewerMode();

        Command command = parser.parseCommand("back");
        assertNotNull(command);
        assertEquals(BackCommand.class, command.getClass());
    }

    @Test
    void parseCommand_byeCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("bye");
        assertNotNull(command);
        assertEquals(ByeCommand.class, command.getClass());
    }

    @Test
    void parseCommand_viewerAddCommand_success() {
        Parser parser = new Parser();
        UI.setPCViewerMode();

        Command command = parser.parseCommand("add abc");
        assertNotNull(command);
        assertEquals(ViewerAddCommand.class, command.getClass());
    }

    @Test
    void parseCommand_viewerDeleteCommand_success() {
        Parser parser = new Parser();
        UI.setPCViewerMode();

        Command command = parser.parseCommand("delete 1");
        assertNotNull(command);
        assertEquals(ViewerDeleteCommand.class, command.getClass());
    }
}

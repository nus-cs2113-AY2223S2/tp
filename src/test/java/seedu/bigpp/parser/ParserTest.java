package seedu.bigpp.parser;

import org.junit.jupiter.api.Test;
import seedu.bigpp.command.Command;
import seedu.bigpp.command.buildercommand.BuilderEditBudgetCommand;
import seedu.bigpp.command.buildercommand.BuilderEditNameCommand;
import seedu.bigpp.command.buildercommand.BuilderInfoCommand;
import seedu.bigpp.command.buildercommand.BuilderListComponentCommand;
import seedu.bigpp.command.buildercommand.BuilderSelectCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomCpuCommand;
import seedu.bigpp.command.commoncommand.BackCommand;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.viewercommand.ViewerAddCommand;
import seedu.bigpp.command.viewercommand.ViewerDeleteCommand;
import seedu.bigpp.command.viewercommand.ViewerEditCommand;
import seedu.bigpp.command.viewercommand.ViewerViewCommand;
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

    @Test
    void parseCommand_viewerViewCommand_success() {
        Parser parser = new Parser();
        UI.setPCViewerMode();

        Command command = parser.parseCommand("view 1");
        assertNotNull(command);
        assertEquals(ViewerViewCommand.class, command.getClass());
    }

    @Test
    void parseCommand_viewerEditCommand_success() {
        Parser parser = new Parser();
        UI.setPCViewerMode();

        Command command = parser.parseCommand("edit 1");
        assertNotNull(command);
        assertEquals(ViewerEditCommand.class, command.getClass());
    }

    // Test cases for Builder commands
    @Test
    void parseCommand_builderListComponentCommand_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("list");
        assertNotNull(command);
        assertEquals(BuilderListComponentCommand.class, command.getClass());
    }

    @Test
    void parseCommand_builderEditNameCommand_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("name NewName");
        assertNotNull(command);
        assertEquals(BuilderEditNameCommand.class, command.getClass());
    }

    @Test
    void parseCommand_builderEditBudgetCommand_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("budget 1000");
        assertNotNull(command);
        assertEquals(BuilderEditBudgetCommand.class, command.getClass());
    }

    @Test
    void parseCommand_builderSelectCommand_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("select cpu 1");
        assertNotNull(command);
        assertEquals(BuilderSelectCommand.class, command.getClass());
    }

    @Test
    void parseCommand_builderInfoCommand_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("info cpu");
        assertNotNull(command);
        assertEquals(BuilderInfoCommand.class, command.getClass());
    }

    @Test
    void parseCommand_builderCustomCpu_success() {
        Parser parser = new Parser();
        UI.setPCBuilderMode(0);

        Command command = parser.parseCommand("custom cpu name|brand|0|1|2|3|4|5|6|scoket");
        assertNotNull(command);
        assertEquals(BuilderCustomCpuCommand.class, command.getClass());
    }
}

package pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.backend.BackendTestUtil;
import pocketpal.frontend.commands.HelpCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test help command")
public class HelpCommandTest extends BackendTestUtil {

    private final HelpCommand testCommand1 = new HelpCommand();
    private final HelpCommand testCommand2 = new HelpCommand("Add");
    private final HelpCommand testCommand3 = new HelpCommand("Delete");

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final String expectedOutput1 = MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_HELP_MENU
            + UIConstants.LINE;

    private final String expectedOutput2 = MessageConstants.MESSAGE_ADD_COMMAND + UIConstants.LINE;

    @Test
    @DisplayName("Test constructor for HelpCommand")
    void testHelpCommand(){
        try {
            assertEquals(null, testCommand1.getCommand());
            assertEquals("Add", testCommand2.getCommand());
            assertEquals("Delete", testCommand3.getCommand());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in HelpCommand")
    void testHelpCommandNullExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand1.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput1.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand2.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput2.toString(), outContent.toString());
    }
}

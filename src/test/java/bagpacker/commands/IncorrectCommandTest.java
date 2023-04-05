package bagpacker.commands;

import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * IncorrectCommandTest to test HelpCommand class
 */
public class IncorrectCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testIncorrectCommand() {
        setUp();
        IncorrectCommand incorrectCmd = new IncorrectCommand("errorType", "helpMessage");
        incorrectCmd.execute(new PackingList());
        String incorrectCmdMessage1 = "/////////////////////////////////////////////////////////////";
        String incorrectCmdMessage2 = "Error errorType:";
        assert(outputStreamCaptor.toString().trim().contains(incorrectCmdMessage1)
                & outputStreamCaptor.toString().trim().contains(incorrectCmdMessage2));
        tearDown();
    }
}

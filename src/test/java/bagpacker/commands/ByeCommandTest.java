package bagpacker.commands;

import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * CommandsTest to test methods in Commands class
 */
public class ByeCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testByeCommand() {
        PackingList dummyPackingList = new PackingList();
        setUp();
        ByeCommand byeCmd = new ByeCommand();
        byeCmd.execute(dummyPackingList);
        assert(!ByeCommand.isBagPackerRunning);
        tearDown();
    }
}

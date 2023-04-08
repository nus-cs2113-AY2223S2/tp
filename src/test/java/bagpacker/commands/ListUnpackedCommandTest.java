package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * IncorrectCommandTest to test HelpCommand class
 */
public class ListUnpackedCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testListUnpackedCommand() {
        PackingList dummyPackingList = new PackingList();
        Item item = new Item(1, "toothbrush");
        Command addCommand = new AddCommand(item);
        addCommand.execute(dummyPackingList);
        addCommand = new AddCommand(new Item(8, "dollars"));
        addCommand.execute(dummyPackingList);
        PackCommand packCommand = new PackCommand(1, 1);
        packCommand.execute(dummyPackingList);
        setUp();
        ListUnpackedCommand listunpackedCmd = new ListUnpackedCommand();
        listunpackedCmd.execute(dummyPackingList);
        String listCmdMessage1 = "Here are the unpacked items in your list";
        String listCmdMessage2 = "[0/8] dollars";
        assert(outputStreamCaptor.toString().trim().contains(listCmdMessage1)
                & outputStreamCaptor.toString().trim().contains(listCmdMessage2));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

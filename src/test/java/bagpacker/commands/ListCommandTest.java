package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * IncorrectCommandTest to test HelpCommand class
 */
public class ListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testListCommand() {
        PackingList dummyPackingList = new PackingList();
        Item item = new Item(1, "toothbrush");
        Command addCommand = new AddCommand(item);
        addCommand.execute(dummyPackingList);
        setUp();
        ListCommand listCmd = new ListCommand();
        listCmd.execute(dummyPackingList);
        String listCmdMessage1 = "Here are the items in your list";
        String listCmdMessage2 = "[0/1] toothbrush";
        assert(outputStreamCaptor.toString().trim().contains(listCmdMessage1)
                & outputStreamCaptor.toString().trim().contains(listCmdMessage2));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void testListCommand_emptyList() {
        PackingList dummyPackingList = new PackingList();
        setUp();
        ListCommand listCmd = new ListCommand();
        listCmd.execute(dummyPackingList);
        String listCmdMessage1 = "There are no items in your list. What would you like to add?";
        String listCmdMessage2 = "____________________________________________________________";
        assert(outputStreamCaptor.toString().trim().contains(listCmdMessage1)
                & outputStreamCaptor.toString().trim().contains(listCmdMessage2));
        tearDown();
    }
}

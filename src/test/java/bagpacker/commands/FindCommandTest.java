package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * CommandsTest to test methods in Commands class
 */
public class FindCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testFindCommand_itemFound() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(10, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        FindCommand findCmd = new FindCommand("pairs of");
        setUp();
        findCmd.execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim().contains("pairs of socks")
                & outputStreamCaptor.toString().trim().contains("[0/10]"));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void testFindCommand_itemNotFound() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(10, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        FindCommand findCmd = new FindCommand("shoes");
        setUp();
        findCmd.execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim()
                .contains("An item containing the description shoes does not exist. Please try again."));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

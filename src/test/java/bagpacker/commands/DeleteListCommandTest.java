package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CommandsTest to test methods in Commands class
 */
public class DeleteListCommandTest {

    @Test
    public void testDeleteListCommand() {
        DeleteListCommand deleteListCmd = new DeleteListCommand();
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(4, "pairs of socks");
        Item secondItem = new Item(10, "underwear");
        dummyPackingList.addItem(firstItem);
        dummyPackingList.addItem(secondItem);
        deleteListCmd.execute(dummyPackingList);
        assertEquals(0, dummyPackingList.size());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

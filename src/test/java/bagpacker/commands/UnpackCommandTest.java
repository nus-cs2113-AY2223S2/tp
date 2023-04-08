package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unpack Command Test to test HelpCommand class
 */
public class UnpackCommandTest {
    @Test
    public void testUnpackCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(30, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        firstItem = PackingList.get(0);
        PackCommand packCmd = new PackCommand(20, 1);
        packCmd.execute(dummyPackingList);
        int initialPackedQty = firstItem.getPackedQuantity();
        UnpackCommand unpackCmd = new UnpackCommand(10, 1);
        unpackCmd.execute(dummyPackingList);
        int newPackedQty = firstItem.getPackedQuantity();
        assertEquals(initialPackedQty - newPackedQty, 10);

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

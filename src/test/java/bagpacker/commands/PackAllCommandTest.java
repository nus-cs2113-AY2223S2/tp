package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PackAll CommandTest to test HelpCommand class
 */
public class PackAllCommandTest {

    @Test
    public void testPackAllCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(2000, "pens");
        dummyPackingList.addItem(firstItem);
        PackAllCommand packAllCmd = new PackAllCommand(1);
        packAllCmd.execute(dummyPackingList);
        firstItem = PackingList.getItemList().get(0);

        // Expect packed quantity to be 2000
        assertEquals(2000, firstItem.getPackedQuantity());
        // Expect packed quantity to be the same as total quantity
        assertEquals(firstItem.getPackedQuantity(), firstItem.getTotalQuantity());

    }

    @Test
    public void testListCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(30, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        PackAllCommand packAllCmd = new PackAllCommand(1);
        packAllCmd.execute(dummyPackingList);
        firstItem = PackingList.getItemList().get(0);
        assertEquals(firstItem.getPackedQuantity(), firstItem.getTotalQuantity());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

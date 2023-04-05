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

package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PackAll CommandTest to test HelpCommand class
 */
public class PackCommandTest {
    @Test
    public void testPackCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(30, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        PackCommand packCmd = new PackCommand(20, 1);
        packCmd.execute(dummyPackingList);
        firstItem = PackingList.getItemList().get(0);
        assertEquals(20, firstItem.getTotalQuantity() - firstItem.getUnpackedQuantity());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

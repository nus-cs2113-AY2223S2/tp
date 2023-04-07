package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UnpackAllCommandTest to test methods in UnpackAllCommand class
 */
public class UnpackAllCommandTest {


    @Test
    public void testUnpackAllCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(2000, "pens");
        dummyPackingList.addItem(firstItem);
        PackAllCommand packAllCmd = new PackAllCommand(1);
        packAllCmd.execute(dummyPackingList);
        firstItem = PackingList.getItemList().get(0);

        // Expect packed quantity to be 2000
        assertEquals(2000, firstItem.getPackedQuantity());

        UnpackAllCommand unpackAllCmd = new UnpackAllCommand(1);
        unpackAllCmd.execute(dummyPackingList);

        // Expect packed quantity to be 0
        assertEquals(0, firstItem.getPackedQuantity());

        // Expect unpacked quantity to be 2000
        assertEquals(2000, firstItem.getUnpackedQuantity());

        // Expect unpacked quantity to be same as total quantity
        assertEquals(firstItem.getUnpackedQuantity(), firstItem.getTotalQuantity());

    }
}



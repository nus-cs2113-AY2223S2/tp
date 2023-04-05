package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CommandsTest to test methods in Commands class
 */
public class EditQuantityCommandTest {

    @Test
    public void testEditQuantityCommand() {
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(10, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        EditQuantityCommand editQtyCmd = new EditQuantityCommand(30, 1);
        editQtyCmd.execute(dummyPackingList);
        firstItem = PackingList.getItemList().get(0);
        assertEquals(30, firstItem.getTotalQuantity());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

}

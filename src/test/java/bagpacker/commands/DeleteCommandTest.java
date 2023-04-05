package bagpacker.commands;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CommandsTest to test methods in Commands class
 */
public class DeleteCommandTest {

    @Test
    public void testDeleteCommand() {
        DeleteCommand deleteCmd = new DeleteCommand(1);
        PackingList dummyPackingList = new PackingList();
        Item firstItem = new Item(4, "pairs of socks");
        dummyPackingList.addItem(firstItem);
        deleteCmd.execute(dummyPackingList);
        assertEquals(0, dummyPackingList.size());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
}

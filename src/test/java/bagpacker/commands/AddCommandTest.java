package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CommandsTest to test methods in Commands class
 */
public class AddCommandTest {

    @Test
    public void testAddCommand() {
        PackingList packingList = new PackingList();
        Item item = new Item(1, "toothbrush");
        Command addCommand = new AddCommand(item);
        addCommand.execute(packingList);

        // Expect a Packinglist with a toothbrush item in first index
        assertEquals("toothbrush", PackingList.get(0).getItemName());
        Assertions.assertNotEquals("tooth", PackingList.get(0).getItemName());

        // Expect second index item in the packing list to be toothpaste
        Item itemTwo = new Item(1, "toothpaste");
        addCommand = new AddCommand(itemTwo);
        addCommand.execute(packingList);

        assertEquals("toothpaste", PackingList.get(1).getItemName());
        Assertions.assertNotEquals("toothbrush", PackingList.get(1).getItemName());

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(packingList);
    }
    @Test
    public void testAddCommand_existingItem() {
        PackingList packingList = new PackingList();
        Item item = new Item(1, "toothbrush");
        Command addCommand = new AddCommand(item);
        addCommand.execute(packingList);

        Item itemTwo = new Item(2, "toothbrush");
        addCommand = new AddCommand(itemTwo);
        addCommand.execute(packingList);

        assert(packingList.size() == 1);
        assertEquals(PackingList.get(0).getTotalQuantity(), 3);

        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(packingList);
    }
}

package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCommandTest {
    Inventory inventory;
    Item newItem;

    @Test
    void addItemToInventory() {
        inventory = new Inventory();
        newItem = new Item("Item 1", "7252727320", 2, 123.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        for(String itemName: newItem.getName().toLowerCase().split(" ")){
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
    }
}

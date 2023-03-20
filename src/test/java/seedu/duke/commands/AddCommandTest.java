package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Inventory;
import seedu.duke.Item;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {
    Inventory inventory;
    Item newItem;

    @Test
    void addItemToInventory() {
        inventory = new Inventory();
        newItem = new Item("Item 1", "72527273070", "2", "123");
        Command command = new AddCommand(inventory, newItem);
        command.run();
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        assertTrue(inventory.getItemNameHash().containsKey(newItem.getName().toLowerCase()));
    }
}
package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for AddCommand.
 * Conducts the following test cases:
 * 1. Add item to inventory
 * 2. Add multiple items to inventory
 * 3. Add duplicate item with same UPC and name
 * 4. Add duplicate item with same UPC but different name
 */
class AddCommandTest {
    Inventory inventory;

    @Test
    void addItemToInventory() {
        inventory = new Inventory();
        Item newItem = new Item("Item 1", "7252727320", 2, 123.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();
        assertEquals("uncategorized", inventory.getItemInventory().get(0).getCategory());
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        for (String itemName : newItem.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
    }

    @Test
    void addItemWithCategoryToInventory() {
        inventory = new Inventory();
        Item newItem = new Item("Item 1", "213131231324", 22, 123.0, "fruit");
        Command command = new AddCommand(inventory, newItem);
        command.run();
        assertEquals("fruit", inventory.getItemInventory().get(0).getCategory());
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        for (String itemName : newItem.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
    }

    @Test
    void addMultipleItemsToInventory() {
        inventory = new Inventory();
        Item newItem1 = new Item("Item 1", "7252727320", 2, 1.50, "fruit");
        Item newItem2 = new Item("Item 2", "4534552342", 20, 1.40);
        Item newItem3 = new Item("Item 3", "3454685754", 200, 1.30, "mobile");

        // Instantiate the Add command objects
        Command command1 = new AddCommand(inventory, newItem1);
        Command command2 = new AddCommand(inventory, newItem2);
        Command command3 = new AddCommand(inventory, newItem3);

        // Run the commands
        command1.run();
        command2.run();
        command3.run();

        // Assertions
        assertEquals(3, inventory.getItemInventory().size());
        assertEquals(newItem1, inventory.getItemInventory().get(0));
        assertEquals(newItem2, inventory.getItemInventory().get(1));
        assertEquals(newItem3, inventory.getItemInventory().get(2));

        for (Item item : inventory.getItemInventory()) {
            assertTrue(inventory.getUpcCodes().containsKey(item.getUpc()));
        }
        for (String itemName : newItem1.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
        for (String itemName : newItem2.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
        for (String itemName : newItem3.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
    }

    @Test
    void addDuplicateItem() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        inventory = new Inventory();
        Item newItem = new Item("Item 1", "7252727320", 2, 123.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();
        command.run();
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        for (String itemName : newItem.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
        String expectedOutput = "Duplicate UPC found! Please add another item with a different UPC";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addDuplicateItemWithDifferentName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        inventory = new Inventory();
        Item newItem = new Item("Item 1", "7252727320", 2, 123.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();
        Item newItem2 = new Item("Item 2", "7252727320", 2, 123.0);
        command = new AddCommand(inventory, newItem2);
        command.run();
        assertEquals(1, inventory.getItemInventory().size());
        assertEquals(newItem, inventory.getItemInventory().get(0));
        assertTrue(inventory.getUpcCodes().containsKey(newItem.getUpc()));
        for (String itemName : newItem.getName().toLowerCase().split(" ")) {
            assertTrue(inventory.getItemNameHash().containsKey(itemName));
        }
        String expectedOutput = "Duplicate UPC found! Please add another item with a different UPC";
        assertTrue(outContent.toString().contains(expectedOutput));
    }


}

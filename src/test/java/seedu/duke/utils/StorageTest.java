package seedu.duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddCommand;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;

import java.time.LocalDateTime;

class StorageTest {

    @Test
    public synchronized void testCSVReadWrite() {
        Inventory testInventory = new Inventory();
        Item testItem = new Item("testItem", "123456789012", 10, 10.0, LocalDateTime.now());
        Item testItem2 = new Item("test Punctuation-items,here", "123456789013", 10, 10.0,
                LocalDateTime.now());
        AddCommand addCommand = new AddCommand(testInventory, testItem);
        addCommand.run();
        addCommand = new AddCommand(testInventory, testItem2);
        addCommand.run();
        Storage.writeCSV(testInventory);
        Assertions.assertTrue(Storage.readCSV(Types.SESSIONFILEPATH).getItemInventory().contains(testItem));
        Assertions.assertTrue(Storage.readCSV(Types.SESSIONFILEPATH).getItemInventory().contains(testItem2));
        Assertions.assertEquals("testItem", Storage.readCSV(Types.SESSIONFILEPATH).getItemInventory().
                get(0).getName());
        Assertions.assertEquals("test Punctuation-items,here", Storage.readCSV(Types.SESSIONFILEPATH).
                getItemInventory().get(1).getName());
    }
}

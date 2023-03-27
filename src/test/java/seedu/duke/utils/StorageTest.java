package seedu.duke.utils;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testCSVReadWrite() {
        Inventory testInventory = new Inventory();
        Item testItem = new Item("testItem", "123456789012", 10, 10.0, LocalDateTime.now());
        Item testItem2 = new Item("testItem2", "123456789013", 10, 10.0, LocalDateTime.now());
        testInventory.getItemInventory().add(testItem);
        testInventory.getItemInventory().add(testItem2);
        Storage.writeCSV(testInventory);
        assertTrue(Storage.readCSV().getItemInventory().contains(testItem));
        assertTrue(Storage.readCSV().getItemInventory().contains(testItem2));
    }
}
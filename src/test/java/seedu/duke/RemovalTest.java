package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class RemovalTest {

    @Test
    void removeItemAtIndex() {
        Item item1 = new Item("appleA", "123", "000", "0.0");
        Item item2 = new Item("apples", "012345678", "5000", "12.0");
        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
        Inventory.addItem(item1);
        Inventory.addItem(item2);
        Inventory.addItem(item3);

        String inputAnswer = "Y\n";
        System.setIn(new ByteArrayInputStream((inputAnswer.getBytes())));
        Inventory.removeItemAtIndex(2, "Y");

        assertTrue(Inventory.getItemList().size() == 2);
    }

    @Test
    void removeByUpc() {
        Item item1 = new Item("appleA", "123", "000", "0.0");
        Item item2 = new Item("apples", "012345678", "5000", "12.0");
        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
        Inventory.addItem(item1);
        Inventory.addItem(item2);
        Inventory.addItem(item3);

        String inputAnswer = "Y\n" + "Y\n" + "y\n";
        System.setIn(new ByteArrayInputStream((inputAnswer.getBytes())));
        //int i = Inventory.removeByUpc("upc/123");

        assertAll(() -> assertEquals(0, Inventory.removeByUpc(item1,"123", "Y")),
                () -> assertEquals(1,Inventory.removeByUpc(item3, "876543210", "y")),
                () -> assertEquals(-1, Inventory.removeByUpc(item2, "012345678", "n")));
    }
}
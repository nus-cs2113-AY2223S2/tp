package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;


public class RemoveTest {
    private Inventory inventory = new Inventory();

    @Test
    public void removeByUpcTest() {
        Item item1 = new Item("appleA", "123", "000", "0.0");
        Item item2 = new Item("apples", "012345678", "5000", "12.0");
        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);

        String inputAnswer = "Y";
        System.setIn(new ByteArrayInputStream((inputAnswer.getBytes())));

        int i = inventory.removeByUpc("upc/123");

        assertTrue(i == 0);
    }

}

package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;

import java.util.ArrayList;
public class RemoveTest {

    Parser testParser;
    @Test
    public void removeByUpcTest() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("appleA", "123", "000", "0.0");
        Item item2 = new Item("apples", "012345678", "5000", "12.0");
        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
        Inventory.addItem(item1);
        Inventory.addItem(item2);
        Inventory.addItem(item3);


//        int i = Inventory.removeByUpc("upc/000");
//        assertTrue(i == -1);
    }

}

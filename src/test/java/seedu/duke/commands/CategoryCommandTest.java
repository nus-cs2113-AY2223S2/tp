package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryCommandTest {
    Inventory inventory = new Inventory();
    HashMap<String, ArrayList<Item>> categoryHash;
    @Test
    void catList() {
        Item item1 = new Item("apples", "012345678", 5000, 12.0, "fruits");
        Item item2 = new Item("oranges", "876543210", 3000, 0.32, "citrus");

        AddCommand addCommand = new AddCommand(inventory, item1);
        addCommand.run();
        AddCommand addCommand1 = new AddCommand(inventory, item2);
        addCommand1.run();
        categoryHash = inventory.getCategoryHash();

        Ui.printCategoryList(categoryHash);
        assertTrue(categoryHash.containsKey("fruits"));
        assertTrue(categoryHash.containsKey("citrus"));
        assertFalse(categoryHash.containsKey("Uncategorized"));
    }

    @Test
    void printCatTable() {
        Item item1 = new Item("apples", "012345678", 5000, 12.0, "fruits");
        Item item2 = new Item("oranges", "876543210", 3000, 0.32, "citrus");

        AddCommand addCommand = new AddCommand(inventory, item1);
        addCommand.run();
        AddCommand addCommand1 = new AddCommand(inventory, item2);
        addCommand1.run();
        categoryHash = inventory.getCategoryHash();

        String expectedOutput = "+-----------------+-----------------------------------------------+" +
                System.lineSeparator() +
                "| Category        | Name: UPC                                     |" +
                System.lineSeparator() +
                "+-----------------+-----------------------------------------------+" +
                System.lineSeparator() +
                "| Fruits          | apples: 012345678                             |" +
                System.lineSeparator() +
                "+-----------------+-----------------------------------------------+" +
                System.lineSeparator() +
                "| Citrus          | oranges: 876543210                            |" +
                System.lineSeparator() +
                "+-----------------+-----------------------------------------------+" +
                System.lineSeparator();
        assertEquals(Ui.printTable(categoryHash), expectedOutput);
    }
}

package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    Inventory inventory;

    @Test
    public void printWithoutWrapping() {
        inventory = new Inventory();
        Item item1 = new Item("apples", "012345678", 5000, 12.0);
        Item item2 = new Item("oranges", "876543210", 3000, 0.32, "citrus");

        inventory.getItemInventory().add(item1);
        inventory.getItemInventory().add(item2);
        inventory.getUpcCodes().put(item1.getUpc(), item1);
        inventory.getUpcCodes().put(item2.getUpc(), item2);
        inventory.getItemNameHash().put(item1.getName().toLowerCase(), new ArrayList<>());
        inventory.getItemNameHash().put(item2.getName().toLowerCase(), new ArrayList<>());
        inventory.getItemNameHash().get(item1.getName().toLowerCase()).add(item1);
        inventory.getItemNameHash().get(item2.getName().toLowerCase()).add(item2);
        inventory.getTrie().add(item1.getName().toLowerCase());
        inventory.getTrie().add(item2.getName().toLowerCase());

        String expectedOutput =
                "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| Index | Name            | UPC          | Quantity | Price    | Category        |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| 0     | apples          | 012345678    | 5000     | $12.00   | Uncategorized   |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| 1     | oranges         | 876543210    | 3000     | $0.32    | citrus          |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator();

        assertEquals(Ui.printTable(inventory.getItemInventory()), expectedOutput);
    }


    @Test
    public void printWithWrapping() {
        inventory = new Inventory();
        Item item1 = new Item("applesorangesgreenbeansredbeans", "012345678", 5000, 12.0);

        inventory.getItemInventory().add(item1);
        inventory.getUpcCodes().put(item1.getUpc(), item1);
        inventory.getItemNameHash().put(item1.getName().toLowerCase(), new ArrayList<>());
        inventory.getItemNameHash().get(item1.getName().toLowerCase()).add(item1);
        inventory.getTrie().add(item1.getName().toLowerCase());

        String expectedOutput =
                "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| Index | Name            | UPC          | Quantity | Price    | Category        |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| 0     | applesorangesgr | 012345678    | 5000     | $12.00   | Uncategorized   |" +
                        System.lineSeparator() +
                        "|       | eenbeansredbean |              |          |          |                 |" +
                        System.lineSeparator() +
                        "|       | s               |              |          |          |                 |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator();
        assertEquals(Ui.printTable(inventory.getItemInventory()), expectedOutput);
    }

    @Test
    public void printSpacesWithWrapping() {
        inventory = new Inventory();
        Item item1 = new Item("red orange yellow green blue violet", "012345678", 5000, 12.0);

        inventory.getItemInventory().add(item1);
        inventory.getUpcCodes().put(item1.getUpc(), item1);
        inventory.getItemNameHash().put(item1.getName().toLowerCase(), new ArrayList<>());
        inventory.getItemNameHash().get(item1.getName().toLowerCase()).add(item1);
        inventory.getTrie().add(item1.getName().toLowerCase());

        String expectedOutput =
                "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| Index | Name            | UPC          | Quantity | Price    | Category        |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator() +
                        "| 0     | red orange      | 012345678    | 5000     | $12.00   | Uncategorized   |" +
                        System.lineSeparator() +
                        "|       | yellow green    |              |          |          |                 |" +
                        System.lineSeparator() +
                        "|       | blue violet     |              |          |          |                 |" +
                        System.lineSeparator() +
                        "+-------+-----------------+--------------+----------+----------+-----------------+" +
                        System.lineSeparator();
        assertEquals(Ui.printTable(inventory.getItemInventory()), expectedOutput);
    }
}

package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    Inventory inventory;

    @Test
    public void printWithoutWrapping() {
        inventory = new Inventory();
        Item item1 = new Item("apples", "012345678", "5000", "12.0");
        Item item2 = new Item("oranges", "876543210", "3000", "0.32");

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

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Command command = new ListCommand(inventory);
        command.run();
        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "\u001B[32mHere are the items in your inventory:\u001B[0m" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        "| Name            | UPC          | Quantity | Price    |" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        "| apples          | 012345678    | 5000     | $12.0    |" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        "| oranges         | 876543210    | 3000     | $0.32    |" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        System.lineSeparator() + "____________________________________________________________" +
                        System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());


    }

    @Test
    public void printWithWrapping() {
        inventory = new Inventory();
        Item item1 = new Item("applesorangesgreenbeansredbeans", "012345678", "5000", "12.0");

        inventory.getItemInventory().add(item1);
        inventory.getUpcCodes().put(item1.getUpc(), item1);
        inventory.getItemNameHash().put(item1.getName().toLowerCase(), new ArrayList<>());
        inventory.getItemNameHash().get(item1.getName().toLowerCase()).add(item1);
        inventory.getTrie().add(item1.getName().toLowerCase());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Command command = new ListCommand(inventory);
        command.run();

        String expectedOutput =
                "____________________________________________________________" + System.lineSeparator() +
                        "\u001B[32mHere are the items in your inventory:\u001B[0m" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        "| Name            | UPC          | Quantity | Price    |" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        "| applesorangesgr | 012345678    | 5000     | $12.0    |" + System.lineSeparator() +
                        "| eenbeansredbean |              |          |          |" + System.lineSeparator() +
                        "| s               |              |          |          |" + System.lineSeparator() +
                        "+-----------------+--------------+----------+----------+" + System.lineSeparator() +
                        System.lineSeparator() + "____________________________________________________________" +
                        System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}

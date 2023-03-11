package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.Ui;

import java.util.ArrayList;
public class ListTest {
    @Test
    public void printWithoutWrapping() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("apples", "012345678", "5000", "12.0");
        Item item2 = new Item("oranges", "876543210", "3000", "0.32");
        items.add(item1);
        items.add(item2);

        String expected =
                "+-----------------+--------------+----------+-------+" + System.lineSeparator() +
                "| Name            | UPC          | Quantity | Price |" + System.lineSeparator() +
                "+-----------------+--------------+----------+-------+" + System.lineSeparator() +
                "| apples          | 012345678    | 5000     | 12.0  |" + System.lineSeparator() +
                "+-----------------+--------------+----------+-------+" + System.lineSeparator() +
                "| oranges         | 876543210    | 3000     | 0.32  |" + System.lineSeparator() +
                "+-----------------+--------------+----------+-------+" + System.lineSeparator();

        assertTrue(Ui.printTable(items).equals(expected));


    }

    @Test
    public void printWithWrapping() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("applesorangesgreenbeansredbeans", "012345678", "5000", "12.0");

        items.add(item1);

        String expected =
                "+-----------------+--------------+----------+-------+" + System.lineSeparator() +
                "| Name            | UPC          | Quantity | Price |" + System.lineSeparator() +
                "+-----------------+--------------+----------+-------+" + System.lineSeparator() +
                "| applesorangesgr | 012345678    | 5000     | 12.0  |" + System.lineSeparator() +
                "| eenbeansredbean |              |          |       |" + System.lineSeparator() +
                "| s               |              |          |       |" + System.lineSeparator() +
                "+-----------------+--------------+----------+-------+" + System.lineSeparator();;

        assertTrue(Ui.printTable(items).equals(expected));
    }

}

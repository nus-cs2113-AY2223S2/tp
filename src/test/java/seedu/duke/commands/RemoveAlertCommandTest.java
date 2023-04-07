package seedu.duke.commands;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.objects.Alert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveAlertCommandTest {
    Inventory inventory;

    @Test
    void removeMinAlert() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "min", "2");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        Command removeAlertCommand = new RemoveAlertCommand(inventory, inventoryUpc, "min");
        removeAlertCommand.run();

        String expectedOutput = "Successfully removed the alert.";
        assertTrue(outContent.toString().contains(expectedOutput));

    }

    @Test
    void removeAlertNonexistentItem() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "max", "10");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        Command removeAlertCommand = new RemoveAlertCommand(inventory, "4321", "max");
        removeAlertCommand.run();

        String expectedOutput = "Command failed! Reason: Item not found in database. Please add item first!";
        assertTrue(outContent.toString().contains(expectedOutput));

    }

    @Test
    void removeNonexistentAlert() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "max", "10");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        Command removeAlertCommand = new RemoveAlertCommand(inventory, inventoryUpc, "min");
        removeAlertCommand.run();

        String expectedOutput = "The alert that you are attempting to remove does not exist.";
        assertTrue(outContent.toString().contains(expectedOutput));
    }
}

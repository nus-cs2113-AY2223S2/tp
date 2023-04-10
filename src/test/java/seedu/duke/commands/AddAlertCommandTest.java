package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.objects.Alert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddAlertCommandTest {

    Inventory inventory;

    @Test
    void addMinAlert() {
        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "min", "2");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        assertTrue(inventory.getAlertList().getMinAlertUpcs().containsKey(inventoryUpc));
    }

    @Test
    void addMaxAlert() {
        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "max", "2");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        assertTrue(inventory.getAlertList().getMaxAlertUpcs().containsKey(inventoryUpc));
    }

    @Test
    void addExistingMinAlert() {
        inventory = new Inventory();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "min", "2");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        Alert alertDuplicate = new Alert(inventoryUpc, "min", "5");
        Command addAlertCommandDuplicate = new AddAlertCommand(inventory, alertDuplicate);
        addAlertCommandDuplicate.run();

        String expectedOutput = "This item already has a minimum alert. Delete the existing one first.";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addInvalidMaxAlert() {
        inventory = new Inventory();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Item newItem = new Item("oranges", "4321", 20, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "min", "10");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        Alert maxAlert = new Alert(inventoryUpc, "max", "5");
        Command addMaxAlertCommand = new AddAlertCommand(inventory, maxAlert);
        addMaxAlertCommand.run();
        String expected = "Maximum value to set an alert must be more than existing minimum alert value of this item.";
        assertTrue(outContent.toString().contains(expected));

    }

    @Test
    void minAlertWarning() {
        inventory = new Inventory();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Item newItem = new Item("oranges", "4321", 10, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
        Alert alert = new Alert(inventoryUpc, "min", "20");
        Command addAlertCommand = new AddAlertCommand(inventory, alert);
        addAlertCommand.run();

        String expected = "ALERT: The quantity of oranges is below the minimum level of 20.";
        assertTrue(outContent.toString().contains(expected));

    }

}

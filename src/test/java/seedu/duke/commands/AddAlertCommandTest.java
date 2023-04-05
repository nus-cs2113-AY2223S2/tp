package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.objects.Alert;
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
}

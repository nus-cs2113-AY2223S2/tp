package seedu.duke.commands;

import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

/**
 * Represents the command to list all items in the inventory.
 */
public class ListCommand extends Command {
    public ListCommand(Inventory inventory) {
        super(inventory);
    }

    /**
     * Checks if the inventory is empty. Prints an error message if the inventory is empty.
     * Otherwise, prints a list of all items.
     */
    private void listItems() {
        if (!itemInventory.isEmpty()) {
            Ui.printSuccessList();
            printList();
            Ui.printLine();
        } else {
            Ui.printEmptyList();
        }
    }

    /**
     * Lists all items in the inventory.
     */
    private void printList() {
        String table = Ui.printTable(itemInventory);
        System.out.println(table);

    }


    /**
     * Executes the list command.
     */
    @Override
    public void run() {
        listItems();
    }
}

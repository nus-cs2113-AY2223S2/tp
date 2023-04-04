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
     * Lists all items in the inventory.
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

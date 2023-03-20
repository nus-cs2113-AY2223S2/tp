package seedu.duke.commands;

import seedu.duke.Inventory;
import seedu.duke.Ui;

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
            String table = Ui.printTable(itemInventory);
            System.out.println(table);
            Ui.printLine();
        } else {
            Ui.printEmptyList();
        }
    }

    /**
     * Executes the list command.
     */
    @Override
    public void run() {
        listItems();
    }
}

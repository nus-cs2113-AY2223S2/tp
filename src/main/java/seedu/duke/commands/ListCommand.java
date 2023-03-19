package seedu.duke.commands;

import seedu.duke.Inventory;
import seedu.duke.Ui;

public class ListCommand extends Command {
    public ListCommand(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void run() {
        if (!itemInventory.isEmpty()) {
            Ui.printSuccessList();
            String table = Ui.printTable(itemInventory);
            System.out.println(table);
            System.out.println(Ui.LINE);
        } else {
            Ui.printInvalidList();
        }
    }
}

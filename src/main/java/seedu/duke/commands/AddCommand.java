package seedu.duke.commands;

import seedu.duke.Inventory;
import seedu.duke.Item;
import seedu.duke.Ui;

import java.util.ArrayList;


public class AddCommand extends Command {
    private final Item item;

    public AddCommand(Inventory inventory, Item item) {
        super(inventory);
        this.item = item;
    }

    /**
     * Add an item object into the inventory
     *
     */
    @Override
    public void run() {
        if (upcCodes.containsKey(item.getUpc())) {
            Ui.printDuplicateAdd();
        } else {
            upcCodes.put(item.getUpc(), item);
            itemInventory.add(item);
            Ui.printSuccessAdd();
            String itemName = item.getName().toLowerCase();
            if (!itemNameHash.containsKey(itemName)) {
                itemNameHash.put(itemName, new ArrayList<>());
            }
            itemNameHash.get(itemName).add(item);
            itemsTrie.add(itemName);
        }
    }
}

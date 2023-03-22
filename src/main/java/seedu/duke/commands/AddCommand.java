package seedu.duke.commands;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

/**
 * Represents the command to add an item to the inventory.
 */

public class AddCommand extends Command {
    private final Item item;

    public AddCommand(Inventory inventory, Item item) {
        super(inventory);
        this.item = item;
    }

    /**
     * Adds an item to the inventory.
     */
    private void addItem() {
        if (upcCodes.containsKey(item.getUpc())) {
            Ui.printDuplicateAdd();
        } else {
            upcCodes.put(item.getUpc(), item);
            itemInventory.add(item);
            Ui.printSuccessAdd();
            String[] itemNames = item.getName().toLowerCase().split(" ");
            for(String itemName: itemNames){
                if (!itemNameHash.containsKey(itemName)) {
                    itemNameHash.put(itemName, new ArrayList<>());
                }
                itemNameHash.get(itemName).add(item);
                itemsTrie.add(itemName);
            }
        }
    }

    /**
     * Executes the add command.
     */
    @Override
    public void run() {
        addItem();
    }
}

package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

/**
 * Represents the command to add an item to the inventory.
 */

public class AddCommand extends Command {
    private final Item item;

    /**
     * Constructor for the AddCommand class.
     *
     * @param inventory The inventory to be initialised in the Command class.
     * @param item      The item to be added to the inventory.
     */
    public AddCommand(final Inventory inventory, final Item item) {
        super(inventory);
        this.item = item;
    }

    private void addCategory() {
        try {
            if (!item.getCategory().isEmpty()) {
                String category = item.getCategory().replaceFirst("c/", "");
                item.setCategory(category);
            }
        } catch (NullPointerException e) {
            item.setCategory("uncategorized");
        }
        try {
            CategoryCommand.updateItemCategory(item, item.getCategory(), item.getCategory());
        } catch (CategoryFormatException e) {
            //Ui.printNewCategory();
        }
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
            addCategory();
            Ui.printSuccessAdd();
            String[] itemNames = item.getName().toLowerCase().split(" ");
            for (String itemName : itemNames) {
                if (!itemNameHash.containsKey(itemName)) {
                    itemNameHash.put(itemName, new ArrayList<>());
                }
                itemNameHash.get(itemName).add(item);
                itemsTrie.add(itemName);
            }
            if (!inventory.getUpcCodesHistory().containsKey(item.getUpc())) {
                inventory.getUpcCodesHistory().put(item.getUpc(), new ArrayList<>());
            }
            inventory.getUpcCodesHistory().get(item.getUpc()).add(new Item(item));
            if (SessionManager.getAutoSave()) {
                SessionManager.writeSession(inventory);
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

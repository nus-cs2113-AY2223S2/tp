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
    private static final String EMPTY_SPACE = " ";
    private static final String UNCATEGORIZED = "Uncategorized";
    private static final String CATEGORY_LABEL = "c/";
    private static final String EMPTY_STRING = "";
    private final Item item;

    /**
     * Constructor for the AddCommand class.
     * @param inventory The inventory to be initialised in the Command class.
     * @param item      The item to be added to the inventory.
     */
    public AddCommand(final Inventory inventory, final Item item) {
        super(inventory);
        this.item = item;
    }

    /**
     * Adds item into category specified. If unspecified, category is default to be "Uncategorized"
     */
    private void addCategory() {
        try {
            if (!item.getCategory().isEmpty()) {
                String category = item.getCategory().replaceFirst(CATEGORY_LABEL, EMPTY_STRING);
                item.setCategory(category);
            }
        } catch (NullPointerException e) {
            item.setCategory(UNCATEGORIZED);
        }
        try {
            CategoryCommand.updateItemCategory(item, item.getCategory(), item.getCategory());
        } catch (CategoryFormatException e) {
            Ui.printNewCategory();
        }
    }
    /**
     * Adds an item to the inventory.
     */
    private void addItem() {
        if (upcCodes.containsKey(item.getUpc())) {
            Ui.printDuplicateAdd();
        } else {
            upcCodes.put(item.getUpc().toLowerCase(), item);
            itemInventory.add(item);
            addCategory();
            Ui.printSuccessAdd();
            String[] itemNames = item.getName().toLowerCase().split(EMPTY_SPACE);
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

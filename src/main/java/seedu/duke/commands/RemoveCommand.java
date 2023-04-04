package seedu.duke.commands;

import seedu.duke.exceptions.RemoveErrorException;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

/**
 * Represents the command to remove an item from the inventory.
 */
public class RemoveCommand extends Command {

    private String upcCode;

    private int itemIndex;

    /**
     * Constructor for RemoveCommand where item is removed from the inventory by its index
     *
     * @param inventory the inventory which item is to be removed from
     * @param itemIndex the index of the item to be removed
     */
    public RemoveCommand(Inventory inventory, int itemIndex) {
        super(inventory);
        this.itemIndex = itemIndex;
    }

    /**
     * Constructor for RemoveCommand where item is removed from the inventory by its UPC Code
     *
     * @param inventory        the inventory which item is to be removed from
     * @param upcCode          the upc code of the item to be removed
     */
    public RemoveCommand(Inventory inventory, String upcCode) {
        super(inventory);
        this.upcCode = upcCode;
    }

    /**
     * Remove an item from the inventory by the upc code given
     */

    public void removeByUpcCode() {
        try {
            if (!upcCodes.containsKey(upcCode)) {
                throw new RemoveErrorException();
            }
        } catch (RemoveErrorException e) {
            Ui.printItemNotFound();
            return;
        }
        Item itemToRemove = new Item(upcCodes.get(upcCode));
        int indexOfItem = itemInventory.indexOf(itemToRemove);
        String category = itemToRemove.getCategory();
        inventory.getUpcCodesHistory().remove(upcCode);
        itemInventory.remove(indexOfItem);
        upcCodes.remove(upcCode);
        String[] itemNames = itemToRemove.getName().toLowerCase().split(" ");
        for (String itemName : itemNames) {
            removeItemFromHashTrie(itemToRemove, itemName);
        }
        removeItemFromCategoryHash(itemToRemove, category);
        removeAlert(upcCode);
        Ui.printSuccessRemove(itemToRemove);

    }

    /**
     * Remove an item from the inventory by the index given
     */
    public void removeByIndex() {
        Item itemToRemove;
        try {
            itemToRemove = new Item(itemInventory.get(itemIndex));
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printItemNotFound();
            return;
        }
        String upcCode = itemToRemove.getUpc();
        String category = itemToRemove.getCategory();
        int i = itemInventory.indexOf(itemToRemove);
        upcCodes.remove(upcCode);
        inventory.getUpcCodesHistory().remove(upcCode);
        itemInventory.remove(i);
        String[] itemNames = itemToRemove.getName().toLowerCase().split(" ");
        for (String name : itemNames) {
            removeItemFromHashTrie(itemToRemove, name);
        }
        removeItemFromCategoryHash(itemToRemove, category);
        removeAlert(upcCode);
        Ui.printSuccessRemove(itemToRemove);
    }

    private void removeItemFromHashTrie(Item itemToRemove, String itemName) {
        if (itemNameHash.get(itemName).size() == 1) {
            itemNameHash.remove(itemName);
            itemsTrie.remove(itemName);
        } else {
            itemNameHash.get(itemName).remove(itemToRemove);
        }
    }

    private void removeItemFromCategoryHash(Item itemToRemove, String category) {
        category = category.toLowerCase();
        if (categoryHash.get(category).size() == 1) {
            categoryHash.get(category).remove(itemToRemove);
            categoryHash.remove(category);
        } else {
            categoryHash.get(category).remove(itemToRemove);
        }
    }

    private void removeAlert(String upcCode) {
        AlertList alertList = inventory.getAlertList();
        if (alertList.getMinAlertUpcs().containsKey(upcCode)) {
            alertList.getMinAlertUpcs().remove(upcCode);
        }
        if (alertList.getMaxAlertUpcs().containsKey(upcCode)) {
            alertList.getMaxAlertUpcs().remove(upcCode);
        }
    }

    /**
     * Executes the remove command.
     */
    @Override
    public void run() {
        try {
            if (upcCode != null) {
                removeByUpcCode();
            } else {
                removeByIndex();
            }
            if (SessionManager.getAutoSave()) {
                SessionManager.writeSession(inventory);
            }
        } catch (NullPointerException | NumberFormatException e) {
            Ui.printItemNotFound();
        }

    }
}

package seedu.duke.commands;

import seedu.duke.exceptions.RemoveErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

/**
 * Represents the command to remove an item from the inventory.
 */
public class RemoveCommand extends Command {

    private String upcCode;
    private String userConfirmation;

    private int itemIndex;


    /**
     * Constructor for RemoveCommand where item is removed from the inventory by its index
     *
     * @param inventory        the inventory which item is to be removed from
     * @param itemIndex        the index of the item to be removed
     * @param userConfirmation Y/N confirmation from user input
     */
    public RemoveCommand(Inventory inventory, int itemIndex, String userConfirmation) {
        super(inventory);
        this.itemIndex = itemIndex;
        this.userConfirmation = userConfirmation;

    }

    /**
     * Constructor for RemoveCommand where item is removed from the inventory by its UPC Code
     *
     * @param inventory        the inventory which item is to be removed from
     * @param upcCode          the upc code of the item to be removed
     * @param userConfirmation Y/N confirmation from user input
     */
    public RemoveCommand(Inventory inventory, String upcCode, String userConfirmation) {
        super(inventory);
        this.upcCode = upcCode;
        this.userConfirmation = userConfirmation;
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
        Item itemToRemove = upcCodes.get(upcCode);
        switch (userConfirmation.toUpperCase()) {
        case "Y":
            int indexOfItem = itemInventory.indexOf(itemToRemove);
            upcCodes.remove(upcCode);
            itemInventory.remove(indexOfItem);
            String[] itemNames = itemToRemove.getName().toLowerCase().split(" ");
            for (String itemName : itemNames) {
                if (itemNameHash.get(itemName).size() == 1) {
                    itemNameHash.remove(itemName);
                    itemsTrie.remove(itemName);
                } else {
                    itemNameHash.get(itemName).remove(itemToRemove);
                }
            }
            Ui.printSuccessRemove(itemToRemove);
            break;
        case "N":
            Ui.printNotRemoving();
            break;
        default:
            Ui.printInvalidReply();
            break;
        }
    }

    /**
     * Remove an item from the inventory by the index given
     */
    public void removeByIndex() {
        Item itemToRemove;
        try {
            itemToRemove = itemInventory.get(itemIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printItemNotFound();
            return;
        }
        switch (userConfirmation.toUpperCase()) {
        case "Y":
            String itemName = itemToRemove.getName().toLowerCase();
            String upcCode = itemToRemove.getUpc();
            int i = itemInventory.indexOf(itemToRemove);
            upcCodes.remove(upcCode);
            itemInventory.remove(i);
            if (itemNameHash.get(itemName).size() == 1) {
                itemNameHash.remove(itemName);
                itemsTrie.remove(itemName);
            } else {
                itemNameHash.get(itemName).remove(itemToRemove);
            }
            Ui.printSuccessRemove(itemToRemove);
            break;
        case "N":
            Ui.printNotRemoving();
            break;
        default:
            Ui.printInvalidReply();
            break;
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
            System.out.println("NULL");
        }

    }
}

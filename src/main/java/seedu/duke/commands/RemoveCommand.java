package seedu.duke.commands;

import seedu.duke.Inventory;
import seedu.duke.Item;
import seedu.duke.Ui;

public class RemoveCommand extends Command {
    /**
     * //     * Remove an item from the inventory by the index given
     * //     *
     * //     * @param itemIndex        item of index to be removed the inventory
     * //     * @param inputConfirmation Y/N confirmation from user input
     * //
     */
    public RemoveCommand(Inventory inventory, int itemIndex, String inputConfirmation) {
        super(inventory);
        this.itemNameHash = inventory.getItemNameHash();
        Item itemToRemove = itemInventory.get(itemIndex);
        switch (inputConfirmation.toUpperCase()) {
        case "Y":
            String itemName = itemToRemove.getName().toLowerCase();
            String upcCode = itemToRemove.getUpc();
            int i = itemInventory.indexOf(itemToRemove);
            inventory.getUpcCodes().remove(upcCode);
            itemInventory.remove(i);
            if (itemNameHash.get(itemName).size() == 1) {
                itemNameHash.remove(itemName);
                inventory.getTrie().remove(itemName);
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

    public RemoveCommand(Inventory inventory, String upcCode, String confirmation) {
        super(inventory);
        this.itemInventory = inventory.getItemInventory();
        this.itemNameHash = inventory.getItemNameHash();
        this.upcCodes = inventory.getUpcCodes();
        Item itemToRemove = inventory.getUpcCodes().get(upcCode);
        switch (confirmation.toUpperCase()) {
        case "Y":
            String itemName = itemToRemove.getName().toLowerCase();
            int indexOfItem = itemInventory.indexOf(itemToRemove);
            upcCodes.remove(upcCode);
            itemInventory.remove(indexOfItem);
            if (itemNameHash.get(itemName).size() == 1) {
                itemNameHash.remove(itemName);
                inventory.getTrie().remove(itemName);
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


    @Override
    public void run() {
        if (!itemInventory.isEmpty()) {
            Ui.printSuccessList();
            String table = Ui.printTable(itemInventory);
            System.out.println(table);
            Ui.printLine();
        } else {
            Ui.printInvalidList();
        }
    }
}

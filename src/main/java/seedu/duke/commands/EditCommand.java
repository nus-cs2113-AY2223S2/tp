package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;
import seedu.duke.exceptions.EditErrorException;

import java.util.ArrayList;

/**
 * Represents the command to edit an item in the inventory.
 */
public class EditCommand extends Command {

    private static final String NAME_LABEL = "n/";
    private static final String QUANTITY_LABEL = "qty/";
    private static final String PRICE_LABEL = "p/";
    private static final String CATEGORY_LABEL = "c/";
    private final String[] editInfo;

    public EditCommand(Inventory inventory, String[] editInfo) {
        super(inventory);
        this.editInfo = editInfo;
    }

    /**
     * Searches the Hashmap to obtain the item required to be interacted with by the user.
     *
     * @param editInfo The array of strings that contain the user inputs.
     * @return Returns the variable of type "Item", which is the item in question to be interacted with by the user.
     * @throws EditErrorException Exception related to all errors generated by the edit command.
     */
    public Item retrieveItemFromHashMap(final String[] editInfo) throws EditErrorException {
        String upcCode = editInfo[0].replaceFirst("upc/", "");
        if (!upcCodes.containsKey(upcCode)) {
            throw new EditErrorException();
        }
        return upcCodes.get(upcCode);
    }

    /**
     * Executes method to edit item attributes in the list and prints an error string if the user's edit command
     * inputs were incorrectly written.
     *
     * @param item The target item in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws NumberFormatException      Exception related to all invalid number formats inputted.
     */
    public void updateItemInfo(final Item item, final Item oldItem, final String[] data) throws
            MissingParametersException, NumberFormatException {
        try {
            handleUserEditCommands(item, oldItem, data);
        } catch (MissingParametersException mpe) {
            throw new MissingParametersException();
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        } catch (CategoryFormatException e) {
            Ui.printInvalidCategory();
        }
    }

    /**
     * Detects specific chars in the array of individual strings, and executes the change of item attribute values
     * (i.e, Name, Quantity, Price) based on the first few chars detected in the individual string.
     *
     * @param item The target item in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws NumberFormatException      Exception related to all invalid number formats inputted.
     */
    private void handleUserEditCommands(Item item, Item oldItem, String[] data) throws
            MissingParametersException, NumberFormatException, CategoryFormatException {
        String currentLabel = "null";
        try {
            validateUserEditCommands(data);
            for (int dataSequence = 1; dataSequence < data.length; dataSequence += 1) {
                currentLabel = makeEdits(item, data, currentLabel, dataSequence);
            }
        } catch (MissingParametersException mpe) {
            throw new MissingParametersException();
        } catch (NumberFormatException nfe) {
            revertChanges(item, oldItem);
            throw new NumberFormatException();
        }
    }

    /**
     * In the event that edits were not made due to errors, revert attributes back to old attributes.
     *
     * @param item The item whose attributes are to be edited.
     * @param oldItem The item containing the old attributes before the edit was made.
     */
    private void revertChanges(Item item, Item oldItem) {
        item.setName(oldItem.getName());
        item.setPrice(oldItem.getPrice());
        item.setQuantity(oldItem.getQuantity());
        item.setCategory(oldItem.getCategory());
    }

    /**
     * Make specific edits to an attribute based on the inputs of the user, which can range from quantity to price
     * to category and name.
     *
     * @param item The target item in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @param currentLabel The current attribute type being edited.
     * @param dataSequence The numerical index of the string array containing the user commands.
     * @return String containing the name of the attribute type currently being edited.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     */
    private static String makeEdits(Item item, String[] data, String currentLabel, int dataSequence)
            throws MissingParametersException {
        if (data[dataSequence].contains("n/")) {
            String newName = data[dataSequence].replaceFirst("n/", "");
            item.setName(newName);
            currentLabel = NAME_LABEL;
        } else if (data[dataSequence].contains("qty/")) {
            String updatedQuantity = data[dataSequence].replaceFirst("qty/", "");
            setItemQuantity(item, updatedQuantity);
            currentLabel = QUANTITY_LABEL;
        } else if (data[dataSequence].contains("p/")) {
            String updatedPrice = data[dataSequence].replaceFirst("p/", "");
            setItemPrice(item, updatedPrice);
            currentLabel = PRICE_LABEL;
        } else if (data[dataSequence].contains("c/")) {
            String updatedCategory = data[dataSequence].replaceFirst("c/", "");
            updatedCategory = updatedCategory.toLowerCase();
            item.setCategory(updatedCategory);
            currentLabel = CATEGORY_LABEL;
        } else {
            if (currentLabel.equals(NAME_LABEL)) {
                item.setName(item.getName() + " " + data[dataSequence]);
            } else {
                throw new MissingParametersException();
            }
        }
        return currentLabel;
    }

    /**
     * Ensures that the edit command formatting is valid before passing the input onwards for edit processing.
     *
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     */
    private void validateUserEditCommands(String[] data) throws  MissingParametersException {
        int upcEditCount = 0;
        int nameEditCount = 0;
        int quantityEditCount = 0;
        int priceEditCount = 0;
        int categoryEditCount = 0;
        String currentLabel = "null";
        for (int dataSequence = 1; dataSequence < data.length; dataSequence += 1) {
            if (data[dataSequence].contains("n/")) {
                nameEditCount += 1;
                currentLabel = NAME_LABEL;
            } else if (data[dataSequence].contains("qty/")) {
                quantityEditCount += 1;
                currentLabel = QUANTITY_LABEL;
            } else if (data[dataSequence].contains("p/")) {
                priceEditCount += 1;
                currentLabel = PRICE_LABEL;
            } else if (data[dataSequence].contains("upc/")) {
                upcEditCount += 1;
            } else if (data[dataSequence].contains("c/")) {
                categoryEditCount += 1;
                currentLabel = CATEGORY_LABEL;
            } else {
                if (!currentLabel.equals(NAME_LABEL)) {
                    throw new MissingParametersException();
                }
            }
        }
        if (nameEditCount > 1 || quantityEditCount > 1 || priceEditCount > 1 || categoryEditCount > 1
                || upcEditCount >= 1) {
            throw new MissingParametersException();
        }
    }

    /**
     * Sets the item price to a specific value according to the user input.
     *
     * @param item         The target item in which the user wants to edit.
     * @param updatedPrice The new price of the item.
     * @throws NumberFormatException Exception related to all number conversion errors.
     */
    private static void setItemPrice(Item item, String updatedPrice) throws NumberFormatException {
        try {
            double newPrice = Double.parseDouble(updatedPrice);
            if (newPrice >= 0) {
                item.setPrice(newPrice);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
    }

    /**
     * Sets the item quantity to a specific value according to the user input.
     *
     * @param item            The target item in which the user wants to edit.
     * @param updatedQuantity The new quantity of the item.
     * @throws NumberFormatException Exception related to all number conversion errors.
     */
    private static void setItemQuantity(Item item, String updatedQuantity) throws NumberFormatException {
        try {
            int newQuantity = Integer.parseInt(updatedQuantity);
            if (newQuantity >= 0) {
                item.setQuantity(newQuantity);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
    }

    /**
     * Edit Command that searches for the item in the ArrayList and changes the item attributes according
     * to the wishes of the user.
     */
    public void setEditInfo() {
        try {
            Item updatedItem = retrieveItemFromHashMap(editInfo);
            Item oldItem = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            updateItemInfo(updatedItem, oldItem, editInfo);
            CategoryCommand.updateItemCategory(oldItem, oldItem.getCategory(),updatedItem.getCategory());
            Item itemForHistory = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            handleTrie(updatedItem, oldItem);
            upcCodes.remove(oldItem.getUpc());
            upcCodes.put(updatedItem.getUpc(), updatedItem);
            Ui.printEditDetails(oldItem, updatedItem);

            inventory.getAlertList().checkAlerts(updatedItem.getUpc(), updatedItem.getName(),
                    upcCodes.get(updatedItem.getUpc()).getQuantity().intValue());

            inventory.getUpcCodesHistory().get(oldItem.getUpc()).add(itemForHistory);
            if (SessionManager.getAutoSave()) {
                SessionManager.writeSession(inventory);
            }
        } catch (EditErrorException eee) {
            Ui.printItemNotFound();
        } catch (MissingParametersException mpe) {
            Ui.printInvalidEditCommand();
        } catch (NumberFormatException nfe) {
            Ui.printInvalidPriceOrQuantityEditInput();
        } catch (CategoryFormatException e) {
            Ui.printInvalidCategory();
        }
    }

    public void handleTrie(Item updatedItem, Item oldItem) {
        String[] oldItemNames = oldItem.getName().toLowerCase().split(" ");
        String newItemNamesFull = updatedItem.getName().toLowerCase();
        for (String oldItemName : oldItemNames) {
            if (!newItemNamesFull.contains(oldItemName) && itemNameHash.get(oldItemName).size() == 1) {
                itemNameHash.remove(oldItemName);
                itemsTrie.remove(oldItemName);
            } else {
                itemNameHash.get(oldItemName).remove(oldItem);
            }
        }
        String[] newItemNames = newItemNamesFull.split(" ");
        for (String newItemName : newItemNames) {
            if (!itemNameHash.containsKey(newItemName)) {
                itemNameHash.put(newItemName, new ArrayList<>());
            }
            itemNameHash.get(newItemName).add(updatedItem);
            itemsTrie.add(newItemName);
        }
    }

    /**
     * Executes the Edit Command
     */
    @Override
    public void run() {
        setEditInfo();
    }
}

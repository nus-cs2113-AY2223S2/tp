package seedu.duke.commands;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.RestockErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

public class RestockCommand extends Command{
    private final String[] restockInfo;

    public RestockCommand(Inventory inventory, String[] restockInfo) {
        super(inventory);
        this.restockInfo = restockInfo;
    }

    /**
     * Check for wrong quantity inputs by the user, such as negative values, zero values and integers exceeding
     * the 100 million limit.
     *
     * @param quantity The user input for how much is to be added to the existing quantity of the item.
     * @param oldQuantity The previous quantity count of the item.
     * @throws RestockErrorException Exception related to all errors associated with the "restock" command.
     */
    private void checkQuantityValidity(int quantity, int oldQuantity) throws RestockErrorException {
        int maxQuantity = 99999999;
        int minQuantity = 0;
        if (quantity <= minQuantity || quantity + oldQuantity < minQuantity || quantity + oldQuantity > maxQuantity) {
            throw new RestockErrorException();
        }
    }

    /**
     * Increases the quantity of an item by a user defined amount and detects for invalid user inputs.
     *
     * @param item The target item in the ArrayList in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws NumberFormatException Exception related to all invalid number formats inputted.
     * @throws RestockErrorException Exception related to all errors associated with the "restock" command.
     */
    private void handleUserRestockCommands(Item item, String data) throws MissingParametersException,
            NumberFormatException, RestockErrorException {
        if (data.startsWith("qty/")) {
            String updatedQuantity = data.replaceFirst("qty/", "");
            try {
                int quantityToAdd = Integer.parseInt(updatedQuantity);
                int oldQuantity = item.getQuantity();
                checkQuantityValidity(quantityToAdd, oldQuantity);
                item.setQuantity(oldQuantity + quantityToAdd);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException();
            } catch (RestockErrorException ree) {
                throw new RestockErrorException();
            }
        } else {
            throw new MissingParametersException();
        }
    }

    /**
     * Perform checks for whether user inputs for the "restock" command has too many parameters (exceeds 2).
     *
     * @param restockInfo The array of strings containing all the user inputs for the "restock" command.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     */
    private void checkRestockCommandLength(String[] restockInfo) throws MissingParametersException {
        if (restockInfo.length != 2) {
            throw new MissingParametersException();
        }
    }

    /**
     * Executes method to perform addition of stock to the item specified by the user and throws exceptions
     * for error message printing if the incorrect inputs from the user are detected.
     *
     * @param item The target item in the ArrayList in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws RestockErrorException Exception related to all errors associated with the "restock" command.
     */
    public void updateItemQuantity(final Item item, final String data) throws MissingParametersException,
            RestockErrorException {
        try {
            handleUserRestockCommands(item, data);
        } catch (MissingParametersException mpe) {
            throw new MissingParametersException();
        } catch (NumberFormatException | RestockErrorException nfe) {
            throw new RestockErrorException();
        }
    }

    /**
     * "Restock" Command that searches for the item in the ArrayList and increases the item quantity according
     * to the wishes of the user by executing a series of methods.
     */
    public void restockItem() {
        try {
            EditCommand itemToRestock = new EditCommand(inventory, restockInfo);
            Item updatedItem = itemToRestock.retrieveItemFromHashMap(restockInfo);
            Item oldItem = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            checkRestockCommandLength(restockInfo);
            updateItemQuantity(updatedItem, restockInfo[1]);
            Item itemForHistory = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            itemToRestock.handleTrie(updatedItem, oldItem);
            upcCodes.remove(oldItem.getUpc());
            upcCodes.put(updatedItem.getUpc(), updatedItem);
            Ui.printRestockDetails(oldItem, updatedItem);

            inventory.getAlertList().checkAlerts(updatedItem.getUpc(), updatedItem.getName(),
                    upcCodes.get(updatedItem.getUpc()).getQuantity().intValue());

            inventory.getUpcCodesHistory().get(oldItem.getUpc()).add(itemForHistory);
            if (SessionManager.getAutoSave()) {
                SessionManager.writeSession(inventory);
            }
        } catch (EditErrorException eee) {
            Ui.printItemNotFound();
        } catch (MissingParametersException mpe) {
            Ui.printInvalidRestockCommand();
        } catch (RestockErrorException ree) {
            Ui.printInvalidAddQuantityInput();
        }
    }

    /**
     * Executes the Restock Command
     */
    @Override
    public void run() {
        restockItem();
    }
}

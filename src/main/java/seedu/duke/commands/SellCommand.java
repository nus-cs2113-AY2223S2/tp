package seedu.duke.commands;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.SellErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

public class SellCommand extends Command{

    private final String[] sellInfo;

    public SellCommand(Inventory inventory, String[] sellInfo) {
        super(inventory);
        this.sellInfo = sellInfo;
    }

    /**
     * Check for wrong quantity inputs by the user, such as negative and zero values, quantities exceeding total
     * current quantity.
     *
     * @param quantityToDeduct The user input for how much is to be deducted from the existing quantity of the item.
     * @param currentQuantity The current quantity levels of the item in question.
     * @throws SellErrorException Exception related to all errors associated with the "restock" command.
     */
    private void checkQuantityValidity(int quantityToDeduct, int currentQuantity) throws SellErrorException {
        if (quantityToDeduct <= 0 || currentQuantity < quantityToDeduct) {
            throw new SellErrorException();
        }
    }

    /**
     * Perform checks for whether user inputs for the "sell" command has too many parameters (exceeds 2).
     *
     * @param sellInfo The array of strings containing all the user inputs for the "sell" command.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     */
    private void checkSellCommandLength(String[] sellInfo) throws MissingParametersException {
        if (sellInfo.length != 2) {
            throw new MissingParametersException();
        }
    }

    /**
     * Increases the quantity of an item by a user defined amount and detects for invalid user inputs.
     *
     * @param item The target item in the ArrayList in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws NumberFormatException Exception related to all invalid number formats inputted.
     * @throws SellErrorException Exception related to all errors associated with the "sell" command.
     */
    private void handleUserSellCommands(Item item, String data) throws MissingParametersException,
            NumberFormatException, SellErrorException {
        if (data.startsWith("qty/")) {
            String updatedQuantity = data.replaceFirst("qty/", "");
            try {
                int quantityToDeduct = Integer.parseInt(updatedQuantity);
                checkQuantityValidity(quantityToDeduct, item.getQuantity());
                int oldQuantity = item.getQuantity();
                assert oldQuantity >= quantityToDeduct : "Quantity to be deducted is more than available stock!";
                item.setQuantity(oldQuantity - quantityToDeduct);
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException();
            } catch (SellErrorException ree) {
                throw new SellErrorException();
            }
        } else {
            throw new MissingParametersException();
        }
    }

    /**
     * Executes method to perform deduction of stock to the item specified by the user and throws exceptions
     * for error message printing if the incorrect inputs from the user are detected.
     *
     * @param item The target item in the ArrayList in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     * @throws MissingParametersException Exception related to all errors due to missing parameters.
     * @throws SellErrorException Exception related to all errors associated with the "sell" command.
     */
    private void updateItemQuantity(Item item, String data) throws MissingParametersException,
            SellErrorException {
        try {
            handleUserSellCommands(item, data);
        } catch (MissingParametersException mpe) {
            throw new MissingParametersException();
        } catch (NumberFormatException | SellErrorException nfe) {
            throw new SellErrorException();
        }
    }

    /**
     * "Sell" Command that searches for the item in the ArrayList and decreases the item quantity according
     * to the wishes of the user by executing a series of methods.
     */
    public void sellItem() {
        try {
            EditCommand itemToSell = new EditCommand(inventory, sellInfo);
            Item updatedItem = itemToSell.retrieveItemFromHashMap(sellInfo);
            Item oldItem = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            checkSellCommandLength(sellInfo);
            updateItemQuantity(updatedItem, sellInfo[1]);
            Item itemForHistory =  new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory());
            itemToSell.handleTrie(updatedItem, oldItem);
            upcCodes.remove(oldItem.getUpc());
            upcCodes.put(updatedItem.getUpc(), updatedItem);
            Ui.printSellDetails(oldItem, updatedItem);

            inventory.getAlertList().checkAlerts(updatedItem.getUpc(), updatedItem.getName(),
                    upcCodes.get(updatedItem.getUpc()).getQuantity().intValue());

            inventory.getUpcCodesHistory().get(oldItem.getUpc()).add(itemForHistory);
            if (SessionManager.getAutoSave()) {
                SessionManager.writeSession(inventory);
            }
        } catch (EditErrorException eee) {
            Ui.printItemNotFound();
        } catch (MissingParametersException mpe) {
            Ui.printInvalidSellCommand();
        } catch (SellErrorException see) {
            Ui.printInvalidDeductQuantityInput();
        }
    }

    /**
     * Executes the Sell Command
     */
    @Override
    public void run() {
        sellItem();
    }
}

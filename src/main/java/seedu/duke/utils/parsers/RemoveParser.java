package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.RemoveErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.HashMap;

public class RemoveParser extends Parser {
    private static final String EMPTY_SPACE = " ";
    private static final String BY_UPC = "f/upc";
    private static final String BY_INDEX = "f/index";
    private static final int INT_INDEX = 1;
    private static final int VALID_COMMAND_LENGTH = 2;
    private static final int TYPE_INT = 0;


    public RemoveParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    /**
     * Parses the user input for the "remove" command by index.
     *
     * @param commands  The user input split into an array of strings.
     * @param inventory The inventory to be modified.
     * @throws MissingParametersException if input by user has missing parameters
     */
    private static void parseRemoveByIndex(final String[] commands, Inventory inventory)
            throws MissingParametersException {
        if (commands.length < VALID_COMMAND_LENGTH) {
            throw new MissingParametersException();
        }
        int itemIndex = Integer.parseInt(commands[INT_INDEX]);
        Command removeCommand = new RemoveCommand(inventory, itemIndex);
        removeCommand.run();
    }

    /**
     * Parses the user input for the "remove" command by UPC.
     *
     * @param commands  The user input split into an array of strings.
     * @param inventory The inventory to be modified.
     * @throws MissingParametersException if input by user has missing paramaters
     * @throws RemoveErrorException if unable to remove item
     */
    private static void parseRemoveByUpc(final String[] commands, Inventory inventory)
            throws MissingParametersException, RemoveErrorException {
        if (commands.length < VALID_COMMAND_LENGTH) {
            throw new MissingParametersException();
        }
        String upcCode = commands[INT_INDEX];
        HashMap<String, Item> upcCodes = inventory.getUpcCodes();
        if (!upcCode.matches("(\\d+)") || !upcCodes.containsKey(upcCode)) {
            throw new RemoveErrorException();
        }
        Command removeCommand = new RemoveCommand(inventory, upcCode);
        removeCommand.run();
    }

    /**
     * Handles the "remove" command by making sure that formatting is correct, before passing the user inputs
     */
    @Override
    public void run() {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            String[] commands = rawInput.split(EMPTY_SPACE);
            switch (commands[TYPE_INT]) {
            case BY_UPC:
                parseRemoveByUpc(commands, inventory);
                break;
            case BY_INDEX:
                parseRemoveByIndex(commands, inventory);
                break;
            default:
                throw new MissingParametersException();
            }
        } catch (MissingParametersException e) {
            e.missingRemoveItemParameters();
        } catch (RemoveErrorException e) {
            Ui.printInvalidUpc(inventory);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex(inventory);
        }
    }
}

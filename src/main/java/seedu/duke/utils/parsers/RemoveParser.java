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
    private static final int VALID_COMMAND_LENGTH = 2;


    public RemoveParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    /**
     * Parses the user input for the "remove" command by index.
     *
     * @param commands  The user input split into an array of strings.
     * @param inventory The inventory to be modified.
     * @throws MissingParametersException
     */
    private static void parseRemoveByIndex(final String[] commands, Inventory inventory)
            throws MissingParametersException {
        if (commands.length < VALID_COMMAND_LENGTH) {
            throw new MissingParametersException();
        }
        int itemIndex = Integer.parseInt(commands[1]);
        Command removeCommand = new RemoveCommand(inventory, itemIndex);
        removeCommand.run();
    }

    /**
     * Parses the user input for the "remove" command by UPC.
     *
     * @param commands  The user input split into an array of strings.
     * @param inventory The inventory to be modified.
     * @throws MissingParametersException
     * @throws RemoveErrorException
     */
    private static void parseRemoveByUpc(final String[] commands, Inventory inventory)
            throws MissingParametersException, RemoveErrorException {
        if (commands.length < VALID_COMMAND_LENGTH) {
            throw new MissingParametersException();
        }
        String upcCode = commands[1];
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
            String[] commands = rawInput.split(" ");
            switch (commands[0]) {
            case "f/upc":
                parseRemoveByUpc(commands, inventory);
                break;
            case "f/index":
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

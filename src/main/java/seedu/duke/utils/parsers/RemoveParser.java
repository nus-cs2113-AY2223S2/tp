package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.RemoveErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.HashMap;
import java.util.Scanner;

public class RemoveParser extends Parser{
    private static Scanner in = new Scanner(System.in);
    public RemoveParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }
    private static void parseRemoveByIndex(final String[] commands, Inventory inventory)
            throws MissingParametersException {
        if (commands.length == 1) {
            throw new MissingParametersException();
        }
        Item itemToRemove;
        String confirmation;
        int itemIndex = Integer.parseInt(commands[1]);
        itemToRemove = inventory.getItemInventory().get(itemIndex);
        Ui.printConfirmMessage(itemToRemove);
        confirmation = in.nextLine();
        Command removeCommand = new RemoveCommand(inventory, itemIndex, confirmation);
        removeCommand.run();
    }

    private static void parseRemoveByUpc(final String[] commands, Inventory inventory)
            throws MissingParametersException, RemoveErrorException {
        String confirmation;
        Item itemToRemove;
        if (commands.length == 1 || !commands[1].startsWith("upc/")) {
            throw new MissingParametersException();
        }
        String upcCode = commands[1].replaceFirst("upc/", "");
        HashMap<String, Item> upcCodes = inventory.getUpcCodes();
        if (!upcCode.matches("(\\d+)") || !upcCodes.containsKey(upcCode)) {
            throw new RemoveErrorException();
        }
        itemToRemove = upcCodes.get(upcCode);
        Ui.printConfirmMessage(itemToRemove);
        confirmation = in.nextLine();
        Command removeCommand = new RemoveCommand(inventory, upcCode, confirmation);
        removeCommand.run();
    }
    /**
     * Handles the "remove" command by making sure that formatting is correct, before passing the user inputs
     */
    @Override
    public void run(){
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            String[] commands = rawInput.split(" ");
            switch (commands[0]) {
            case "f/item":
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

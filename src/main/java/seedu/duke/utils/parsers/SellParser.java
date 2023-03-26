package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.SellCommand;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class SellParser extends Parser {
    public SellParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }

    /**
     * Processes the "sell" command and prints an error message if wrong inputs from the user are detected.
     */
    @Override
    public void run(){
        String[] sellInfo = rawInput.split(" ");
        try {
            if (!sellInfo[0].contains("upc/") || sellInfo.length == 1) {
                throw new EditErrorException();
            }
            assert sellInfo[0].contains("upc/") : "UPC Code is not present in user restock command!";
            Command restockCommand = new SellCommand(inventory, sellInfo);
            restockCommand.run();
        } catch (EditErrorException eee) {
            Ui.printInvalidSellCommand();
        }
    }
}

package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.RestockCommand;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class RestockParser extends Parser{
    public RestockParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }

    /**
     * Processes the "restock" command and prints an error message if wrong inputs from the user are detected.
     */
    @Override
    public void run(){
        String[] restockInfo = rawInput.split(" ");
        try {
            if (!restockInfo[0].contains("upc/") || restockInfo.length == 1) {
                throw new EditErrorException();
            }
            assert restockInfo[0].contains("upc/") : "UPC Code is not present in user restock command!";
            Command restockCommand = new RestockCommand(inventory, restockInfo);
            restockCommand.run();
        } catch (EditErrorException eee) {
            Ui.printInvalidRestockCommand();
        }
    }
}

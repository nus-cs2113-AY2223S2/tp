package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class EditParser extends Parser{
    public EditParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }
    @Override
    public void run(){
        String[] editInfo = rawInput.split(" ");
        try {
            if (!editInfo[0].contains("upc/") || editInfo.length == 1) {
                throw new EditErrorException();
            }
            assert editInfo[0].contains("upc/") : "UPC Code is not present in user command!";
            Command editCommand = new EditCommand(inventory, editInfo);
            editCommand.run();
        } catch (EditErrorException eee) {
            Ui.printInvalidEditCommand();
        }
    }
}

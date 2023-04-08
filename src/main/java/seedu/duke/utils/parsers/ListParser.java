package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class ListParser extends Parser{
    public ListParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }


    /**
     * Parses the "list" command and prints an error message if the command format is invalid.
     */
    @Override
    public void run(){
        if (rawInput.isEmpty()) {
            Command listCommand = new ListCommand(inventory);
            listCommand.run();
        } else {
            Ui.printInvalidList();
        }
    }
}

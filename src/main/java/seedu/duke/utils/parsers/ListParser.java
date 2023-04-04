package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class ListParser extends Parser{
    public ListParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }
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

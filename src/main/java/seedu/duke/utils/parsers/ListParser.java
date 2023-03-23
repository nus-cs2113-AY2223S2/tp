package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.objects.Inventory;

public class ListParser extends Parser{
    public ListParser(Inventory inventory){
        super(inventory);
    }
    @Override
    public void run(){
        Command listCommand = new ListCommand(inventory);
        listCommand.run();
    }
}

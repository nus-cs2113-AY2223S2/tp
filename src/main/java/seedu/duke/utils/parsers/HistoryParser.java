package seedu.duke.utils.parsers;

import seedu.duke.commands.HistoryCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Inventory;

public class HistoryParser extends Parser{
    public HistoryParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }

    @Override
    public void run() {
        rawInput = rawInput.trim();
        try{
            if(rawInput.split(" ").length!=1){
                throw new MissingParametersException();
            }
        }catch(MissingParametersException e){
            e.missingHistoryItemParameters();
            return;
        }
        HistoryCommand historyCommand = new HistoryCommand(inventory, rawInput);
        historyCommand.run();
    }
}

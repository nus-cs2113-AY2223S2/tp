package seedu.duke.utils.parsers;

import seedu.duke.commands.HistoryCommand;
import seedu.duke.exceptions.HistoryErrorException;
import seedu.duke.objects.Inventory;

public class HistoryParser extends Parser{

    protected static final int HISTORY_COMMAND_LENGTH = 1;

    public HistoryParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }

    /**
     * Trims the input and then runs the history parser.
     */
    @Override
    public void run() {
        rawInput = rawInput.trim();
        parseHistory();
    }

    /**
     * Handles the "history" command by checking the validity of search term provided before passing to
     * the relevant command.
     *
     */
    private void parseHistory() {
        try{
            if(rawInput.split(" ").length!= HISTORY_COMMAND_LENGTH || rawInput.length()<HISTORY_COMMAND_LENGTH){
                throw new HistoryErrorException();
            }
        }catch(HistoryErrorException e){
            e.incorrectParameters();
            return;
        }
        HistoryCommand historyCommand = new HistoryCommand(inventory, rawInput);
        historyCommand.run();
    }
}

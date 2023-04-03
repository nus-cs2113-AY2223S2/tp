package seedu.duke.stubsmocks;

import seedu.duke.exceptions.HistoryErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.HistoryParser;

public class HistoryParserMock extends HistoryParser {
    private boolean isValidParse;
    public HistoryParserMock(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }
    public boolean isValidParse(){
        return isValidParse;
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
     * Mimics the logic of the parseHistory command in the HistoryParser class and sets the variable isValidParse
     * to whether the parse would have been successful
     *
     */
    private void parseHistory() {
        try{
            if(rawInput.split(" ").length!=HISTORY_COMMAND_LENGTH || rawInput.length()<HISTORY_COMMAND_LENGTH){
                throw new HistoryErrorException();
            }
        }catch(HistoryErrorException e){
            isValidParse = false;
            return;
        }
        isValidParse = true;
    }
}

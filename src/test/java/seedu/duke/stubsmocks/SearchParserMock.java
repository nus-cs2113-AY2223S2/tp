package seedu.duke.stubsmocks;


import seedu.duke.objects.Inventory;
import seedu.duke.types.Types;
import seedu.duke.utils.parsers.SearchParser;


public class SearchParserMock extends SearchParser {
    private boolean isValidParse;
    public SearchParserMock(String rawInput, Inventory inventory, Types.SearchType searchType) {
        super(rawInput, inventory, searchType);
    }
    public boolean isValidParse(){
        return isValidParse;
    }
    /**
     * Mimics the logic of the parseSearchUPC command in the SearchParser class and sets the variable isValidParse
     * to whether the parse would have been successful
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    @Override
    public void parseSearchUPC(String rawInput, Inventory inventory, Types.SearchType searchType) {
        if (rawInput.split(" ").length > SEARCH_UPC_COMMAND_LENGTH || rawInput.length() == EMPTY) {
            isValidParse = false;
            return;
        }
        isValidParse = true;
    }
    /**
     * Mimics the logic of the parseSearch command in the SearchParser class and sets the variable isValidParse
     * to whether the parse would have been successful
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    @Override
    public void parseSearch(String rawInput, Inventory inventory, Types.SearchType searchType) {
        if (rawInput == null || rawInput.length() == EMPTY) {
            isValidParse = false;
            return;
        }
        isValidParse = true;
    }
    @Override
    public void run(){
        if(searchType == Types.SearchType.KEYWORD){
            parseSearch(rawInput, inventory, searchType);
        }else{
            parseSearchUPC(rawInput, inventory, searchType);
        }
    }
}

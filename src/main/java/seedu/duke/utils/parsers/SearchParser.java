package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.SearchCommand;
import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.types.Types;

public class SearchParser extends Parser{
    private Types.SearchType searchType;
    public SearchParser(String rawInput, Inventory inventory, Types.SearchType searchType){
        super(rawInput, inventory);
        this.searchType = searchType;
    }

    /**
     * Handles the "searchUPC" command by checking the validity of search term provided before passing to
     * the relevant command
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    public void parseSearchUPC(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput.split(" ").length > 1 || rawInput.length() == 0) {
                throw new SearchFilterErrorException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (SearchFilterErrorException e) {
            e.incorrectSearchUPCParameters();
        }
    }

    /**
     * Handles the "search" command by checking the validity of search term provided before passing to
     * the relevant command
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    public void parseSearch(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput == null || rawInput.length() == 0) {
                throw new SearchFilterErrorException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (SearchFilterErrorException e) {
            e.incorrectSearchParameters();
        }
    }

    /**
     * Delegates the executes the correct parser method.
     *
     */
    @Override
    public void run(){
        if(searchType == Types.SearchType.KEYWORD){
            parseSearch(rawInput, inventory, searchType);
        }else{
            parseSearchUPC(rawInput, inventory, searchType);
        }
    }
}

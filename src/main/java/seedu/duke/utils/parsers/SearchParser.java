package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.SearchCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.types.Types;
import seedu.duke.utils.Ui;

public class SearchParser extends Parser{
    private Types.SearchType searchType;
    public SearchParser(String rawInput, Inventory inventory, Types.SearchType searchType){
        super(rawInput, inventory);
        this.searchType = searchType;
    }

    /**
     * Handles the "searchUPC" command by checking the validity of search term provided before passing to
     * the searchUPC function
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    public void parseSearchUPC(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            if (rawInput.split(" ").length > 1) {
                throw new SearchFilterErrorException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (MissingParametersException e) {
            e.missingSearchItemParameters();
        } catch (SearchFilterErrorException se) {
            Ui.printInvalidEditCommand();
        }
    }

    /**
     * Handles the "search" command by checking the validity of search term provided before passing to
     * the search function
     *
     * @param rawInput The user input string to be validated.
     * @param inventory The inventory in which the search is done
     * @param searchType The type of search to be conducted
     */
    public void parseSearch(String rawInput, Inventory inventory, Types.SearchType searchType) {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }
            Command searchCommand = new SearchCommand(inventory, rawInput, searchType);
            searchCommand.run();
        } catch (MissingParametersException e) {
            e.missingSearchItemParameters();
        }
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

package seedu.duke.commands;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;
import seedu.duke.types.Types;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the command to search for an item in the inventory.
 */
public class SearchCommand extends Command {
    public static final int FIRST_FOUND = 1;
    private String input;
    private Types.SearchType searchType;

    /**
     * Constructor for SearchCommand class which takes in the inventory and the keyword
     * to search for items in the inventory by the keyword provided
     *
     * @param inventory inventory of items
     * @param input   alphanumeric keyword which user provides for querying
     */
    public SearchCommand(Inventory inventory, String input, Types.SearchType searchType) {
        super(inventory);
        this.input = input;
        this.searchType = searchType;
    }

    /**
     * Search for an item in the inventory by its unique UPC number and returns search query
     */

    public Item searchUPC() {
        if (!upcCodes.containsKey(input)) {
            Ui.printEmptySearch();
            return null;
        }
        return upcCodes.get(input);
    }

    /**
     * Search for an item in the inventory by its keyword and returns search query
     */
    public ArrayList<Item> searchKeyword() {
        ArrayList<Item> results = new ArrayList<>();
        HashMap<Item, Integer> resultItemsCount = new HashMap<>();
        String[] inputs = input.split(" ");
        for(String currentInput: inputs) {
            ArrayList<String> resultNames = itemsTrie.prefixFind(currentInput);
            ArrayList<Item> resultItems = new ArrayList<>();
            for (String name : resultNames) {
                for (Item item : itemNameHash.get(name)) {
                    if (!resultItems.contains(item)) {
                        resultItems.add(item);
                    }
                }
            }
            for(Item item: resultItems){
                if(!resultItemsCount.containsKey(item)){
                    resultItemsCount.put(item, FIRST_FOUND);
                }else{
                    int count = resultItemsCount.get(item);
                    resultItemsCount.replace(item, count+1);
                }
            }
        }
        for(Item item: resultItemsCount.keySet()){
            if(resultItemsCount.get(item) == inputs.length){
                results.add(item);
            }
        }
        if (results.isEmpty()) {
            Ui.printEmptySearch();
            return null;
        }
        return results;
    }

    /**
     * Delegate and executes search command for an item in the inventory by keyword or upc
     */
    @Override
    public void run() {
        if(searchType==Types.SearchType.KEYWORD){
            ArrayList<Item> searchResults = searchKeyword();
            if(searchResults != null){
                Ui.printSearchItems(searchResults);
            }
        } else{
            Item searchResult = searchUPC();
            if(searchResult != null){
                Ui.printSearchUPCItem(searchResult);
            }
        }
    }
}

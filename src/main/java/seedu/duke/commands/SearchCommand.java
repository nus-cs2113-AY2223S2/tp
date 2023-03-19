package seedu.duke.commands;

import seedu.duke.Inventory;
import seedu.duke.Item;
import seedu.duke.Ui;

import java.util.ArrayList;

public class SearchCommand extends Command {
    private String keyword;

    /**
     * Constructor for SearchCommand class which takes in the inventory and the keyword
     * to search for items in the inventory by the keyword provided
     *
     * @param inventory inventory of items
     * @param keyword   alphanumeric keyword which user provides for querying
     */
    public SearchCommand(Inventory inventory, final String keyword) {
        super(inventory);
        this.keyword = keyword;
    }

    // TODO: Split the search feature into 2 here, ternary condition within run() to determine
    //  if search should be by UPC or keyword correspondingly

    /**
     * Search for an item in the inventory by its unique UPC number and returns search query
     *
     * @param upc numeric UPC number which user provides for querying
     */
    private void searchUPC(final String upc) {
        if (!upcCodes.containsKey(upc)) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printSearchUPCItem(upcCodes.get(upc));
    }

    private void searchKeyword() {

    }

    /**
     * Search for an item in the inventory by keyword and displays the search query
     */
    @Override
    public void run() {
        ArrayList<String> resultNames = itemsTrie.prefixFind(keyword);
        if (resultNames.size() == 0) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printLine();
        ArrayList<Item> resultItems = new ArrayList<>();
        for (String name : resultNames) {
            for (Item item : itemNameHash.get(name)) {
                resultItems.add(item);
            }
        }
        Ui.printSearchItems(resultItems);
        Ui.printLine();
    }
}

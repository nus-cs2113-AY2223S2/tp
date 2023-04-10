package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.FilterCommand;
import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;


public class FilterParser extends Parser{

    protected static final int PRICE_INDEX = 2;
    protected static final int PRICE_MODE_INDEX = 1;
    protected static final int KEYWORD_START_INDEX = 1;
    protected static final int PRICE_LENGTH = 3;
    protected static final int CATEGORY_LENGTH = 2;
    protected static final int CATEGORY_FLAG = 0;
    protected static final int FILTER_MODE_FLAG = 0;

    public FilterParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }

    /**
     * Handles the "filter f/price" command by checking the validity of search term provided before passing to the
     * relevant command.
     *
     * @param commands filter type and price
     * @param inventory inventory to sort the items
     */
    protected void parseFilterPrice(String[] commands, Inventory inventory) {
        try {
            double price = Double.parseDouble(commands[PRICE_INDEX]);
            switch (commands[PRICE_MODE_INDEX]) {
            case "p/lt":
            case "p/gt":
            case "p/let":
            case "p/get":
                Command filterCommand = new FilterCommand(inventory, price, commands[1]);
                filterCommand.run();
                break;
            default:
                throw new SearchFilterErrorException();
            }
        } catch (SearchFilterErrorException e) {
            e.missingPriceComparator();
        } catch (NumberFormatException numberFormatException) {
            Ui.printDoubleNeeded();
        }
    }

    /**
     * Handles the "filter f/[category]" command by checking the validity of search term provided before
     * passing to the relevant command.
     *
     * @param commands keywords in a string array
     * @param mode filter mode
     * @param inventory inventory to filter items from
     */
    protected void parseFilterCategory(String[] commands, String mode, Inventory inventory) {
        StringBuilder sb = new StringBuilder();
        for (int i = KEYWORD_START_INDEX; i < commands.length; i++) {
            sb.append(commands[i]);
            sb.append(' ');
        }
        String keyword = sb.toString().trim();
        Command filterCommand = new FilterCommand(inventory, keyword, mode);
        filterCommand.run();
    }

    /**
     * First checks if there is actual input.
     * Next delegates parsing to the correct method.
     *
     */
    @Override
    public void run(){
        try {
            if (rawInput.length() == EMPTY) {
                throw new SearchFilterErrorException();
            }
            String[] commands = rawInput.split(" ");
            switch (commands[FILTER_MODE_FLAG]) {
            case "f/price":
                if (commands.length != PRICE_LENGTH) {
                    throw new SearchFilterErrorException();
                }
                parseFilterPrice(commands, inventory);
                break;
            case "f/category":
                if (commands.length < CATEGORY_LENGTH) {
                    throw new SearchFilterErrorException();
                }
                parseFilterCategory(commands, commands[CATEGORY_FLAG], inventory);
                break;
            default:
                throw new SearchFilterErrorException();
            }
        } catch (SearchFilterErrorException e) {
            e.incorrectFilterParameters();
        }
    }
}

package seedu.duke.stubsmocks;


import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;

import seedu.duke.utils.parsers.FilterParser;

public class FilterParserMock extends FilterParser {
    private boolean isValidParse;
    public FilterParserMock(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    public boolean isValidParse(){
        return isValidParse;
    }
    /**
     * Mimics the logic of the parseFilterPrice command in the FilterParser class and sets the variable isValidParse
     * to whether the parse would have been successful
     *
     * @param commands filter type and price
     * @param inventory inventory to sort the items
     */
    @Override
    public void parseFilterPrice(String[] commands, Inventory inventory) {
        try {
            double price = Double.parseDouble(commands[PRICE_INDEX]);
            switch (commands[PRICE_MODE_INDEX]) {
            case "p/lt":
            case "p/gt":
            case "p/let":
            case "p/get":
                isValidParse = true;
                break;
            default:
                throw new SearchFilterErrorException();

            }
        } catch (SearchFilterErrorException | NumberFormatException e) {
            isValidParse = false;
        }
    }

    /**
     * Mimics the logic of the parseFilterCategory command in the FilterParser class and sets the variable isValidParse
     * to whether the parse would have been successful
     *
     * @param commands keywords in a string array
     * @param mode filter mode
     * @param inventory inventory to filter items from
     */
    @Override
    public void parseFilterCategory(String[] commands, String mode, Inventory inventory) {
        String keyword = "";
        for (int i = KEYWORD_START_INDEX; i < commands.length; i++) {
            keyword += commands[i];
            keyword += ' ';
        }
        keyword = keyword.trim();
        isValidParse = true;
    }
    /**
     * First checks if there is actual input.
     * Next delegates parsing to the correct method.
     *
     */
    @Override
    public void run(){
        try {
            if (rawInput == null) {
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
                isValidParse = false;
                throw new SearchFilterErrorException();
            }
        } catch (SearchFilterErrorException e) {
            isValidParse = false;
        }
    }
}

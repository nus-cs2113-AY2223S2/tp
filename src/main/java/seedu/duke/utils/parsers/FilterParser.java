package seedu.duke.utils.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.FilterCommand;
import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class FilterParser extends Parser{
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
    private void parseFilterPrice(String[] commands, Inventory inventory) {
        try {
            double price = Double.parseDouble(commands[2]);
            switch (commands[1]) {
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
    private void parseFilterCategory(String[] commands, String mode, Inventory inventory) {
        String keyword = "";
        for (int i = 1; i < commands.length; i++) {
            keyword += commands[i];
            keyword += ' ';
        }
        keyword = keyword.trim();
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
            if (rawInput == null) {
                throw new SearchFilterErrorException();
            }
            String[] commands = rawInput.split(" ");
            switch (commands[0]) {
            case "f/price":
                if (commands.length != 3) {
                    throw new SearchFilterErrorException();
                }
                parseFilterPrice(commands, inventory);
                break;
            case "f/category":
                if (commands.length < 2) {
                    throw new SearchFilterErrorException();
                }
                parseFilterCategory(commands, commands[0], inventory);
                break;
            default:
                throw new SearchFilterErrorException();
            }
        } catch (SearchFilterErrorException e) {
            e.incorrectFilterParameters();
        }
    }
}

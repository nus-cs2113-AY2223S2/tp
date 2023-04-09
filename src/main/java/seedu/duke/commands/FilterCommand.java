package seedu.duke.commands;

import seedu.duke.exceptions.SearchFilterErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

/**
 * Represents the command to filter items in the inventory.
 */
public class FilterCommand extends Command {
    private static final String LESS_THAN_FLAG = "p/lt";
    private static final String GREATER_THAN_FLAG = "p/gt";
    private static final String LESS_OR_EQUAL_THAN_FLAG = "p/let";
    private static final String GREATER_OR_EQUAL_THAN_FLAG = "p/get";
    private static final String TYPE_CATEGORY = "f/category";
    private static ArrayList<Item> filteredCategory;
    private String filterType;
    private String filterValue;
    private Double filterPrice;

    /**
     * Constructor for FilterCommand by Category/Tag.
     *
     * @param inventory  Inventory object.
     * @param value      String value to filter by.
     * @param filterMode String category/tag filter mode.
     */
    public FilterCommand(Inventory inventory, final String value, final String filterMode) {
        super(inventory);
        this.filterType = filterMode;
        this.filterValue = value;
    }

    /**
     * Constructor for FilterCommand by Price.
     *
     * @param inventory       Inventory object.
     * @param price           Double value to filter by.
     * @param filterPriceMode String price filtering mode.
     */
    public FilterCommand(Inventory inventory, final double price, final String filterPriceMode) {
        super(inventory);
        this.filterType = filterPriceMode;
        this.filterPrice = price;
    }

    /**
     * Filter items in the inventory by category/tag.
     *
     * @param category String category to filter items by.
     */
    private void filterCategory(String category) throws SearchFilterErrorException {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        }
        if (category.isEmpty()) {
            throw new SearchFilterErrorException();
        } else if (!categoryHash.containsKey(category)) {
            throw new NullPointerException();
        }
        ArrayList<Item> tempFilteredItems = new ArrayList<>();
        for (Item item : itemInventory) {
            if (item.getCategory().toLowerCase().equals(category)) {
                tempFilteredItems.add(item);
            }
        }
        setFilteredCategory(tempFilteredItems);
    }

    public void setFilteredCategory(ArrayList<Item> filteredCategory) {
        this.filteredCategory = filteredCategory;
    }

    private static ArrayList<Item> getFilteredCategory() {
        if (filteredCategory.isEmpty()) {
            return null;
        }

        return filteredCategory;
    }

    /**
     * Filter items in the inventory by price.
     *
     * @param price Double value to filter items by.
     * @param mode  String price filtering mode.
     *              p/lt - price less than
     *              p/gt - price greater than
     *              p/let - price less than or equal to
     *              p/get - price greater than or equal to
     */
    private ArrayList<Item> filterPrice(final double price, final String mode) {
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (Item item : itemInventory) {
            switch (mode) {
            case LESS_THAN_FLAG:
                if (item.getPrice() < price) {
                    filteredItems.add(item);
                }
                break;
            case GREATER_THAN_FLAG:
                if (item.getPrice() > price) {
                    filteredItems.add(item);
                }
                break;
            case LESS_OR_EQUAL_THAN_FLAG:
                if (item.getPrice() <= price) {
                    filteredItems.add(item);
                }
                break;
            case GREATER_OR_EQUAL_THAN_FLAG:
                if (item.getPrice() >= price) {
                    filteredItems.add(item);
                }
                break;
            default:
                break;
            }
        }
        if (filteredItems.isEmpty()) {
            return null;
        }
        return filteredItems;
    }

    /**
     * Gets a list on filtered items based on the filter mode.
     *
     * @return ArrayList Item of filtered items. If no items are found, returns null instead.
     */

    public ArrayList<Item> getFilteredItems() {
        ArrayList<Item> filteredItems = new ArrayList<>();
        switch (filterType) {
        case TYPE_CATEGORY:
            try {
                filterCategory(filterValue);
                filteredItems = getFilteredCategory();
            } catch (NullPointerException e) {
                Ui.printInvalidCategory();
                return null;
            } catch (SearchFilterErrorException e) {
                e.missingCategoryParameters();
                return null;
            }
            break;
        case LESS_THAN_FLAG:
            //fallthrough
        case GREATER_THAN_FLAG:
            //fallthrough
        case LESS_OR_EQUAL_THAN_FLAG:
            //fallthrough
        case GREATER_OR_EQUAL_THAN_FLAG:
            filteredItems = filterPrice(filterPrice, filterType);
            break;
        default:
            break;
        }
        return filteredItems;
    }

    /**
     * Delegate and executes the correct filter command.
     */
    @Override
    public void run() {
        ArrayList<Item> filteredItems = getFilteredItems();
        if (filterType.startsWith(TYPE_CATEGORY)) {
            if (filteredItems != null) {
                Ui.printCategory(filteredItems);
            }
        } else if (filteredItems == null) {
            Ui.printEmptySearch();
        } else {
            Ui.printSearchItems(filteredItems);
        }
    }
}

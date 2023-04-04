package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

public class CategoryCommand extends Command {
    private final String rawInput;

    /**
     * Constructor for the CategoryCommand class.
     * @param inventory the inventory to be initialised in the Command class.
     * @param rawInput the input given by user to execute
     */
    public CategoryCommand(Inventory inventory, String rawInput) {
        super(inventory);
        this.rawInput = rawInput;
    }

    /**
     * Updates an item's category.
     * @param item the item to be updated.
     * @param oldCategory the current category the item is in.
     * @param newCategory the new category the item is going to be in.
     * @throws CategoryFormatException if category provided is invalid.
     */
    public static void updateItemCategory(Item item, String oldCategory, String newCategory)
            throws CategoryFormatException {
        try {
            if (oldCategory.isBlank() || newCategory.isBlank()) {
                throw new CategoryFormatException();
            }
            checkExistingCategory(item, oldCategory, newCategory);
        } catch (CategoryFormatException cfe) {
            throw new CategoryFormatException();
        }
    }

    /**
     * Checks for existing category when about to update an item's category.
     * @param item the item which its category is being updated.
     * @param oldCategory the current category the item belongs to.
     * @param newCategory the new category the item is going to belong to.
     */
    private static void checkExistingCategory(Item item, String oldCategory, String newCategory) {
        try {
            removeItemFromCategory(item, oldCategory);
            item.setCategory(newCategory);
            addItemToCategory(newCategory, item);
        } catch (NullPointerException e) {
            Ui.printNewCategory();
        }
    }

    /**
     * Remove an item from its category upon removal of item or editing its category.
     * @param item the item to be removed from the category hashmap.
     * @param oldCategory the category that the item currently belongs to.
     */
    private static void removeItemFromCategory(Item item, String oldCategory) {
        if (!categoryHash.containsKey(oldCategory)) {
            return;
        }
        if (categoryHash.get(oldCategory).size() == 1) {
            categoryHash.get(oldCategory).remove(item);
            categoryHash.remove(oldCategory);
        } else {
            categoryHash.get(oldCategory).remove(item);
        }
    }

    /**
     * Adds an item into the specified category.
     * @param categoryToAdd the specified category to add item to.
     * @param item the item to be added to a category.
     */
    private static void addItemToCategory(String categoryToAdd, Item item) {
        if (!categoryHash.containsKey(categoryToAdd.toLowerCase())) {
            categoryHash.put(categoryToAdd, new ArrayList<>());
        }
        categoryHash.get(categoryToAdd).add(item);
    }

    /**
     * Prints a table of categories and all items in the categories with their name and UPC.
     */
    private void listCategoryAndItems() {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        }
        Ui.printCategory(categoryHash);
    }

    /**
     * Prints a list of all categories in the inventory.
     */
    private void listAllCategories() {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        } else {
            Ui.printCategoryList(categoryHash);
        }
    }

    /**
     * Executes the category command.
     */
    @Override
    public void run() {
        try {
            if (rawInput.equals("list")) {
                listAllCategories();
            } else if (rawInput.equals("table")) {
                listCategoryAndItems();
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

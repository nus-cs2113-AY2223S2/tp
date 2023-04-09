package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

public class CategoryCommand extends Command {
    private static boolean isAnd;
    private static boolean isOr;
    private static boolean isAt;
    private static boolean isA;
    private static boolean isThe;
    private static boolean isAn;
    private static boolean isNotFirstWord;
    private static boolean isFrom;
    private static boolean isOn;
    private static boolean isFor;
    private static boolean isWith;
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
            if (oldCategory.toLowerCase().isBlank() || newCategory.toLowerCase().isBlank()) {
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
            if (categoryHash.containsValue(item)) {
                removeItemFromCategory(item, oldCategory);
            }
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
    public static void removeItemFromCategory(Item item, String oldCategory) {
        oldCategory = oldCategory.toLowerCase();
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
        categoryToAdd = categoryToAdd.toLowerCase();
        if (!categoryHash.containsKey(categoryToAdd)) {
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
     * Capitalises important words in category name for printing (like book titles).
     * @param category The category to have its words capitalised accordingly.
     * @return
     */
    public static String capitaliseCategory(String category) {
        String capsString = new String();
        String[] catWords = category.split(" ");
        for (String word : catWords) {
            isAnd = word.equals("and");
            isAt = word.equals("at");
            isA = word.equals("a");
            isOr = word.equals("or");
            isThe = word.equals("the");
            isAn = word.equals("an");
            isNotFirstWord = (word != catWords[0]);
            isFrom = word.equals("from");
            isOn = word.equals("on");
            isFor = word.equals("for");
            isWith = word.equals("with");
            if (isAnd || isAt || isOr || isFrom || isWith || isFor || isOn ||
                    ((isA || isThe || isAn) && isNotFirstWord)) {
                capsString = capsString + word;
            } else {
                capsString = capsString + word.substring(0,1).toUpperCase() + word.substring(1);
            }
            capsString = capsString + " ";
        }
        capsString = capsString.trim();
        return capsString;
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

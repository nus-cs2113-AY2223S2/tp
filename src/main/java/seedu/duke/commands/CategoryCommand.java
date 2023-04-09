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
            if (oldCategory.toLowerCase().isBlank() || newCategory.toLowerCase().isBlank()) {
                System.out.println("Exception thrown in updateItem");
                throw new CategoryFormatException();
            }
            System.out.println("Check existing cat");
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
            System.out.println("Try remove item");
            if (categoryHash.containsValue(item)) {
                removeItemFromCategory(item, oldCategory);
            }
            System.out.println("Set item cat");
            item.setCategory(newCategory);
            System.out.println("add item to new cat");
            addItemToCategory(newCategory, item);
        } catch (NullPointerException e) {
            System.out.println("NPE print new cat");
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
            System.out.println("Whats up here");
            categoryHash.get(oldCategory).remove(item);
            System.out.println("Anything can remove?");
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
        System.out.println("does category exist? " + categoryHash.containsKey(categoryToAdd));
        if (!categoryHash.containsKey(categoryToAdd)) {
            System.out.println("contains key already? false");
            System.out.println("category to b added is " + categoryToAdd);
            categoryHash.put(categoryToAdd, new ArrayList<>());
        }
       // System.out.println("Item in this category so far: " + categoryHash.get(categoryToAdd.toLowerCase()).get(0));
        System.out.println("Item to be added into list");
//        Object[] arr = categoryHash.get(categoryToAdd.toLowerCase()).toArray();
//        System.out.println(categoryToAdd.toLowerCase() + " category has " + Arrays.toString(arr));
        categoryHash.get(categoryToAdd).add(item);
        System.out.println(categoryToAdd + "category has " + categoryHash.get(categoryToAdd).toString());
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

    public static String capitaliseCategory(String category) {
        String capsString = new String();
        String[] catWords = category.split(" ");
        for (String word : catWords) {
            if (word.equals("and") || word.equals("at") ||
                    ((word.equals("a") || word.equals("the") || word.equals("an")) && word != catWords[0])) {
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

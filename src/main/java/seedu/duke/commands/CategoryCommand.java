package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

public class CategoryCommand extends Command {
    private final String rawInput;
    private final String[] categoryCommandType;


    public CategoryCommand(Inventory inventory, String rawInput, String[] categoryCommandType) {
        super(inventory);
        this.categoryCommandType = categoryCommandType;
        this.rawInput = rawInput;
    }

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

    private static void checkExistingCategory(Item item, String oldCategory, String newCategory) {
        try {
            removeItemFromCategory(item, oldCategory);
            item.setCategory(newCategory);
            addItemToCategory(newCategory, item);
        } catch (NullPointerException e) {
            Ui.printNewCategory();
        }
    }

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

    private static void addItemToCategory(String categoryToAdd, Item item) {
        if (!categoryHash.containsKey(categoryToAdd.toLowerCase())) {
            categoryHash.put(categoryToAdd, new ArrayList<>());
        }
        categoryHash.get(categoryToAdd).add(item);
    }

    private void listCategoryAndItems() throws NullPointerException {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        }
        Ui.printCategory(categoryHash);
    }

    private void listAllCategories() {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        } else {
            Ui.printCategoryList(categoryHash);
        }
    }

    @Override
    public void run() {
        try {
            if (categoryCommandType[0].equals("list")) {
                listAllCategories();
            } else if (categoryCommandType[0].equals("table")) {
                listCategoryAndItems();
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

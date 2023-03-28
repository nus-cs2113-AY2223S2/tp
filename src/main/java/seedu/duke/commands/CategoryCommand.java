package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryCommand extends Command {
    private final String rawInput;
    private final String[] categoryCommandType;


    public CategoryCommand(Inventory inventory, String rawInput, String[] categoryCommandType) {
        super(inventory);
        this.categoryCommandType = categoryCommandType;
        this.rawInput = rawInput;
    }

    public static void updateItemCategory(Item item, String category) throws CategoryFormatException {
        try {
            if (category.isEmpty() || category.isBlank()) {
                throw new CategoryFormatException();
            }
            checkExistingCategory(item);
        } catch (CategoryFormatException cfe) {
            throw new CategoryFormatException();
        }
    }

    private static void checkExistingCategory(Item item) {
        String category = item.getCategory().toLowerCase();
        try {
            addItemToCategory(category, item);
        } catch (NullPointerException e) {
            Ui.printNewCategory();
        }
    }


    private static void addItemToCategory(String categoryToAdd, Item item) {
        if (!categoryHash.containsKey(categoryToAdd)) {
            ArrayList<Item> newCategoryItemList = new ArrayList<>();
            newCategoryItemList.add(item);
            categoryHash.put(categoryToAdd, newCategoryItemList);
        } else {
            categoryHash.get(categoryToAdd).add(item);
        }
    }

    private void listCategoryAndItems() throws NullPointerException {
        if (categoryHash.isEmpty()) {
            throw new NullPointerException();
        }
        Ui.printCategory(categoryHash);
    }

    private void findCategory(String category) {
        try {
            if (categoryHash.containsKey(category)) {
                ArrayList<Item> items = categoryHash.get(category);
                HashMap<String, ArrayList<Item>> itemsInCategory = new HashMap<>();
                itemsInCategory.put(category, items);
                Ui.printCategory(itemsInCategory);
            } else {
                throw new CategoryFormatException();
            }
        } catch (CategoryFormatException e) {
            if (categoryHash.isEmpty()) {
                Ui.printNoCategoryList();
            } else {
                Ui.printInvalidCategory();
            }
        }
    }

    private void listAllCategories() {
        Ui.printCategoryList(categoryHash);
    }

    @Override
    public void run() {
        try {
            if (categoryCommandType[0].equals("list")) {
                listAllCategories();
            } else if (categoryCommandType[0].equals("table")) {
                listCategoryAndItems();
            } else {
                findCategory(rawInput);
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

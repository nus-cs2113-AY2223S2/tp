package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
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

    private void categoriseItem() {
        try {
            EditCommand itemToCategorise = new EditCommand(inventory, categoryCommandType);
            Item updatedItem = itemToCategorise.retrieveItemFromHashMap(categoryCommandType);
            Item oldItem = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory(), updatedItem.getTags());
            checkCategoryCommandLength(categoryCommandType);
            updateItemCategory(updatedItem, categoryCommandType[1]);
            Item itemForHistory = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
                    updatedItem.getPrice(), updatedItem.getCategory(), updatedItem.getTags());
            Ui.printCategoryDetails(oldItem, updatedItem);
            inventory.getUpcCodesHistory().get(itemForHistory.getUpc()).add(itemForHistory);
        } catch (EditErrorException e) {
            Ui.printItemNotFound();
        } catch (MissingParametersException | CategoryFormatException e) {
            Ui.printBlankCategory();
        }
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

    private void checkCategoryCommandLength(String[] categoryInfo) throws MissingParametersException {
        if (categoryInfo.length != 2) {
            throw new MissingParametersException();
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
//        for (Map.Entry<String, ArrayList<Item>> category : categoryHash.entrySet()) {
//
//        }
//        Ui.printLine();
//        System.out.println(ANSI_BLUE + "Here is the list of categories you have: " + ANSI_RESET);
//        categoryHash.forEach((cat, items) -> System.out.println(cat));
//        Ui.printLine();
        Ui.printCategoryList(categoryHash);
    }

    @Override
    public void run() {
        try {
            if (categoryCommandType[0].equals("list")) {
                listAllCategories();
            } else if (categoryCommandType[0].equals("table")){
                listCategoryAndItems();
            } else {
                findCategory(rawInput);
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

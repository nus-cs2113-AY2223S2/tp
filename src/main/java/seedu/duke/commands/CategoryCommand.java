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
    private final String[] categoryCommandType;

    public CategoryCommand(Inventory inventory, String[] categoryCommandType) {
        super(inventory);
        this.categoryCommandType = categoryCommandType;
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
//            itemToCategorise()
            Ui.printCategoryDetails(oldItem, updatedItem);
            inventory.getUpcCodesHistory().get(itemForHistory.getUpc()).add(itemForHistory);
        } catch (EditErrorException e) {
            Ui.printItemNotFound();
        } catch (MissingParametersException | CategoryFormatException e) {
            Ui.printBlankCategory();
        }
    }

    public void updateItemCategory(Item item, String category) throws CategoryFormatException {
        try {
            if (category.isEmpty() || category.isBlank()) {
                throw new CategoryFormatException();
            }
            String categoryToAdd = category.toLowerCase();
            checkExistingCategory(item);
            addItemToCategory(categoryToAdd, item);
            item.setCategory(categoryToAdd);
        } catch (CategoryFormatException cfe) {
            throw new CategoryFormatException();
        }
    }

    private void checkExistingCategory(Item item) {
        String oldCategory = item.getCategory().toLowerCase();
        try {
            categoryHash.get(oldCategory).remove(item);
            if (categoryHash.get(oldCategory).isEmpty()) {
                categoryHash.remove(oldCategory);
            }
        } catch (NullPointerException e) {
            Ui.printInvalidCategory();
        }
    }



    private void addItemToCategory(String categoryToAdd, Item item) {
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

    private void listAllCategory() throws NullPointerException {
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

    @Override
    public void run() {
        try {
            if (categoryCommandType[0].startsWith("list")) {
                listAllCategory();
            } else if (categoryCommandType[0].startsWith("upc/")) {
                categoriseItem();
            } else {
                findCategory(categoryCommandType[0]);
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

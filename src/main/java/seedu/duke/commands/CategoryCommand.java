package seedu.duke.commands;

import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;

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
//            Item itemForHistory = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity(),
//                    updatedItem.getPrice(), updatedItem.getCategory(), updatedItem.getTags());
//            itemToCategorise()
            Ui.printCategoryDetails(oldItem, updatedItem);
        } catch (EditErrorException e) {
            Ui.printItemNotFound();
        } catch (MissingParametersException | CategoryFormatException e) {
            Ui.printBlankCategory();
        }
    }

    public void updateItemCategory(Item item, String category) throws CategoryFormatException {
        if (category.isBlank() || !category.contains("c/")) {
            throw new CategoryFormatException();
        }
        try {
            category = category.replaceFirst("c/", "");
            if (category.isEmpty() || category.isBlank()) {
                throw new CategoryFormatException();
            }
            String categoryToAdd = category.toLowerCase();
            checkExistingCategory(categoryToAdd, item);
            item.setCategory(categoryToAdd);
        } catch (CategoryFormatException cfe) {
            throw new CategoryFormatException();
        }
    }

    private void checkExistingCategory(String categoryToAdd, Item item) {
        // check if item already has a category
//        if (item.getCategory().equalsIgnoreCase("Uncategorized")) {
//            addItemToCategory(categoryToAdd, item);
//        } else { // already has a category
        String oldCategory = item.getCategory();
        categoryHash.get(oldCategory).remove(item);
        if (categoryHash.get(oldCategory).isEmpty()) {
            categoryHash.remove(oldCategory);
        }
        addItemToCategory(categoryToAdd, item);
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
        Ui.printAllCategory(categoryHash);
    }

    @Override
    public void run() {
        try {
            if (categoryCommandType[0].startsWith("list")) {
                listAllCategory();
            }
//            } else {
//                //findCategory(categoryCommandType[0]);
//            }
            if (categoryCommandType[0].startsWith("upc/")) {
                categoriseItem();
            }
        } catch (NullPointerException npe) {
            System.out.println("Category list does not exist.");
        }
    }

}

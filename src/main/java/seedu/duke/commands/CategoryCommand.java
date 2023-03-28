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
        //for add function (updated for every csv read)
        try {
            if (category.isEmpty() || category.isBlank()) {
                throw new CategoryFormatException();
            }

            checkExistingCategory(item, item.getCategory(), category);

        } catch (CategoryFormatException cfe) {
            throw new CategoryFormatException();
        }
    }

    public static void updateItemCategory(Item item, String oldCategory, String newCategory) throws CategoryFormatException {
        //for edit function
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
            System.out.println("oldcat: " + oldCategory + " newcat: " + newCategory);
            item.setCategory(newCategory);
            addItemToCategory(newCategory, item);
        } catch (NullPointerException e) {
            Ui.printNewCategory();
        }
    }

    private static void removeItemFromCategory(Item item, String oldCategory) {
        System.out.println("now editing: " + item.toString());
        if (oldCategory.equals("uncategorized")) {
            //System.out.println("contains item");
            return;
        }
        if (categoryHash.get(oldCategory).size() == 1) {
            categoryHash.get(oldCategory).remove(item);
            System.out.println("old category is " + oldCategory + " now removing item from categoryHash and removing category");
            System.out.println(categoryHash.get(oldCategory).size());
            categoryHash.remove(oldCategory);
            System.out.println("category map contains " + oldCategory + "? " + categoryHash.containsKey(oldCategory));
        } else {
            System.out.println("old category is " + oldCategory + " now removing item from categoryHash");
            categoryHash.get(oldCategory).remove(item);
        }
    }

//    private static void checkExistingCategory(Item item, String oldCategory) {
//        String category = item.getCategory().toLowerCase();
//        try {
//            System.out.println("after reading csv: ");
//            addItemToCategory(category, item);
//        } catch (NullPointerException e) {
//            Ui.printNewCategory();
//        }
//    }


    private static void addItemToCategory(String categoryToAdd, Item item) {
        //item.setCategory(categoryToAdd);
        System.out.println("now adding: " + item.toString());
        if (!categoryHash.containsKey(categoryToAdd.toLowerCase())) {
//            ArrayList<Item> newCategoryItemList = new ArrayList<>();
//            newCategoryItemList.add(item);
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
            } else {
                findCategory(rawInput);
            }
        } catch (NullPointerException npe) {
            Ui.printNoCategoryList();
        }
    }

}

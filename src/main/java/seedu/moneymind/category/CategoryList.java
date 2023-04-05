package seedu.moneymind.category;

import java.util.ArrayList;

import static seedu.moneymind.string.Strings.CAT_NOT_FOUND;

public class CategoryList {
    public static ArrayList<Category> categories = new ArrayList<Category>();

    /**
     * Add the category to the list.
     */
    public static void addCategory(String category) {
        Category newCategory = new Category(category);
        categories.add(newCategory);
    }

    /**
     * Find the category with specific keyword in the list.
     *
     * @param name the name of the category
     */
    public static void findCategory(String name) {
        int count = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().contains(name)) {
                assert categories.get(i) != null : "Category should not be null";
                count++;
                System.out.println("index " + (i + 1) + ". " + categories.get(i).getName());
            }
            if (count == 0) {
                System.out.println(CAT_NOT_FOUND);
            }
        }
        System.out.println(CAT_NOT_FOUND);
    }

    /**
     * Gets the category with specific index in the list.
     *
     * @param index the index of the category in the list
     * @return the category with specific index in the list
     */
    public static Category getCategory(int index) {
        return categories.get(index);
    }

    /**
     * Delete the category from the list.
     *
     * @param index the index of the category in the list
     */
    public static void deleteCategory(int index) {
        categories.remove(index);
    }

    public static void viewCategoryList() {
        for (int i = 0; i < categories.size(); i++) {
            assert categories.get(i) != null : "Category should not be null";
            System.out.println(i + 1 + ". " + categories.get(i).getName());
        }
    }

    public static void viewEventList(int index) {
        categories.get(index).viewEventList();
    }
}

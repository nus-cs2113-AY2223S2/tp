package seedu.moneymind.category;

import java.util.ArrayList;

/**
 * Represents a list of categories.
 */
public class CategoryList {

    public static ArrayList<Category> categories = new ArrayList<Category>();

    /**
     * Gets the category with specific index in the list.
     *
     * @param index the index of the category in the list
     * @return the category with specific index in the list
     */
    public static Category getCategory(int index) {
        return categories.get(index);
    }

}

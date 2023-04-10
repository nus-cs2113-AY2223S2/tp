package seedu.moneymind.storage;

import java.util.ArrayList;
import java.util.HashMap;

import seedu.moneymind.category.Category;

/**
 * Generates a HashMap of a given ArrayList of categories.
 */
public class GenerateCategoryHashMap {

    /**
     * Generates a HashMap of a given ArrayList of categories.
     *
     * @param savedCategories The ArrayList of categories.
     * @return The HashMap of the categories.
     */
    public static HashMap<String, Integer> generateCategoryHashMap(ArrayList<Category> savedCategories) {
        HashMap<String, Integer> savedCategoryHashMap = new HashMap<>();
        for (int i = 0; i < savedCategories.size(); i++) {
            savedCategoryHashMap.put(savedCategories.get(i).getName(), i);
        }
        return savedCategoryHashMap;
    }

}

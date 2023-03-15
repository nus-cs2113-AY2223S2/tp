package seedu.moneymind.storage;

import java.util.HashMap;

import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;
import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.command.CategoryCommand.categoryMap;

/**
 * Converts a String to a HashMap.
 */
public class LoadToCategoryMap {
    public static void loadToCategoryMap(String input) {
        // hashmap to store the category name and its index in the category list
        HashMap<String, Integer> loadedMap = new HashMap<String, Integer>();
        String[] loadedMapEntries = input.split(NEW_LINE);
        for (String entry : loadedMapEntries) {
            String[] entryComponents = entry.split(STORAGE_NEXT_VARIABLE);
            loadedMap.put(entryComponents[1], Integer.parseInt(entryComponents[2]));
        }
        categoryMap = loadedMap;
    }
}

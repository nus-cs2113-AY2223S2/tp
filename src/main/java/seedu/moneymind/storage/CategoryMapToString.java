package seedu.moneymind.storage;

import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_MAP;
import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;
import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.command.CategoryCommand.categoryMap;

/**
 * Converts categoryMap to a String.
 */
public class CategoryMapToString {
    /**
     * Converts categoryMap to a String.
     * 
     * @return String of categoryMap
     */
    public static String categoryMapToString() {
        String output = STORAGE_CATEGORY_MAP + NEW_LINE;
        for (String key : categoryMap.keySet()) {
            output += STORAGE_NEXT_VARIABLE + key + STORAGE_NEXT_VARIABLE + categoryMap.get(key) + NEW_LINE;
        }
        return output;
    }
}

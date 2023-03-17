package seedu.pocketpal.frontend.util;

import seedu.pocketpal.frontend.constants.EntryConstants;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.frontend.exceptions.InvalidCategoryException;

import static seedu.pocketpal.frontend.constants.MessageConstants.MESSAGE_INVALID_CATEGORY;

public class CategoryUtil {
    public static Category convertStringToCategory(String categoryString) throws InvalidCategoryException {
        switch (categoryString) {
        case EntryConstants.CLOTHING:
            return Category.CLOTHING;
        case EntryConstants.ENTERTAINMENT:
            return Category.ENTERTAINMENT;
        case EntryConstants.FOOD:
            return Category.FOOD;
        case EntryConstants.MEDICAL:
            return Category.MEDICAL;
        case EntryConstants.OTHERS:
            return Category.OTHERS;
        case EntryConstants.PERSONAL:
            return Category.PERSONAL;
        case EntryConstants.UTILITIES:
            return Category.UTILITIES;
        case EntryConstants.TRANSPORTATION:
            return Category.TRANSPORTATION;
        default:
            throw new InvalidCategoryException(MESSAGE_INVALID_CATEGORY);
        }
    }
}

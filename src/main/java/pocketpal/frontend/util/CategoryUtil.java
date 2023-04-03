package pocketpal.frontend.util;

import pocketpal.data.entry.Category;
import pocketpal.frontend.constants.EntryConstants;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidCategoryException;

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
        case EntryConstants.INCOME:
            return Category.INCOME;
        default:
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }
    }
}

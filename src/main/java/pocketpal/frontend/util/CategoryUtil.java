package pocketpal.frontend.util;

import pocketpal.data.entry.Category;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidCategoryException;

public class CategoryUtil {
    public static Category convertStringToCategory(String categoryString) throws InvalidCategoryException {
        try {
            return Category.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }
    }
}

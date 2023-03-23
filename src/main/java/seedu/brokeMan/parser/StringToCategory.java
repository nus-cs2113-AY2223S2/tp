package seedu.brokeMan.parser;


import seedu.brokeMan.entry.Category;
import seedu.brokeMan.exception.CategoryNotCorrectException;


public class StringToCategory {
    public static Category convertStringToCategory(String categoryString) throws CategoryNotCorrectException {
        Category category = null;
        if (categoryString.equals("FOOD")) {
            category = Category.FOOD;
        } else if (categoryString.equals("SHOPPING")) {
            category = Category.SHOPPING;
        } else if (categoryString.equals("GROCERIES")) {
            category = Category.GROCERIES;
        } else if (categoryString.equals("TRANSPORTATION")) {
            category = Category.TRANSPORTATION;
        } else if (categoryString.equals("ENTERTAINMENT")) {
            category = Category.ENTERTAINMENT;
        } else if (categoryString.equals("TRAVEL")) {
            category = Category.TRAVEL;
        } else if (categoryString.equals("SALARY")) {
            category = Category.SALARY;
        } else if (categoryString.equals("INVESTMENT")) {
            category = Category.INVESTMENT;
        } else if (categoryString.equals("OTHERS")){
            category = Category.OTHERS;
        } else {
            throw new CategoryNotCorrectException();
        }
        assert category != null : "Category should be one of the enum tags";
        return category;
    }
}

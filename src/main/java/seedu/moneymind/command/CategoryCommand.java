package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.ui.Ui;
import seedu.moneymind.string.Strings;

import java.util.HashMap;

/**
 * Represents the command to add a new category.
 */

public class CategoryCommand implements Command {
    public static HashMap<String, Integer> categoryMap = new HashMap<String, Integer>();
    private final String name;

    /**
     * Constructs a new CategoryCommand object and adds the category.
     *
     * @param name the name of the category
     */
    public CategoryCommand(String name) {
        this.name = name;
    }

    /**
     * Adds the category.
     */
    @Override
    public void execute(Ui ui) {
        if (categoryMap.get(name) != null) {
            System.out.println(Strings.EXISTED_CATEGORY);
            return;
        }
        Category category = new Category(name);
        CategoryList.categories.add(category);
        categoryMap.put(name, CategoryList.categories.size() - 1);
        System.out.println("New category added: " + name);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

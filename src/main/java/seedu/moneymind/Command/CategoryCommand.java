package seedu.moneymind.command;

import seedu.moneymind.Category;
import seedu.moneymind.CategoryList;
import seedu.moneymind.Ui;

import java.util.HashMap;

public class CategoryCommand implements Command {
    public static HashMap<String, Integer> categoryMap = new HashMap<String, Integer>();
    private final String name;

    public CategoryCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Ui ui) {
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

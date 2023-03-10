package seedu.moneymind;

import java.util.HashMap;

public class CategoryCommand {
    public static HashMap<String, Integer> categoryMap = new HashMap<String, Integer>();
    private String name;

    public CategoryCommand(String name) {
        this.name = name;
        addCategory();
    }
    private void addCategory() {
        Category category = new Category(name);
        CategoryList.categories.add(category);
        categoryMap.put(name, CategoryList.categories.size() - 1);
        System.out.println("New category added: " + name);
    }
}

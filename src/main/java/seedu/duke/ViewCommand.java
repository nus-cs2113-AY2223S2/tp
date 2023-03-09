package seedu.duke;

/**
 * ViewCommand class to view the categories and events.
 */
public class ViewCommand {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String DOT = ".";
    private String categoryName;

    /**
     * Constructs a new ViewCommand object and views the category.
     *
     * @param categoryName the name of the category
     */
    public ViewCommand(String categoryName) {
        this.categoryName = categoryName;
        viewCategory();
    }

    /**
     * Constructs a new ViewCommand object and views all the categories.
     */
    public ViewCommand() {
        viewAll();
    }

    private void viewCategory() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            return;
        }
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        category.viewEventList();
    }

    /**
     * Views all the categories and events.
     */
    private void viewAll() {
        int count = 1;
        for (Category category : CategoryList.categories) {
            System.out.println(count + DOT + category.getName());
            count++;
            // print all the events in the category
            for (Event event : category.events) {
                System.out.println(event.toString());
            }
        }
    }

}

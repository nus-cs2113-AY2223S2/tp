package seedu.moneymind;

/**
 * ViewCommand class to view the categories and events.
 */
public class ViewCommand {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String DOT = ".";
    public static final String NO_CATEGORIES_TO_VIEW = "There are no categories to view";
    public static final String COUNT_ASSERTION = "Count should be greater than 1";
    public static final String NULL_CATEGORY_ASSERTION = "Category name should not be null";
    public static final String NULL_CATEGORY_LIST_ASSERTION = "Category list should not be null";
    private String categoryName;

    /**
     * Constructs a new ViewCommand object and views the category.
     *
     * @param categoryName the name of the category
     */
    public ViewCommand(String categoryName) {
        this.categoryName = categoryName;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        viewCategory();
    }

    /**
     * Constructs a new ViewCommand object and views all the categories.
     */
    public ViewCommand() {
        assert CategoryList.categories != null : NULL_CATEGORY_LIST_ASSERTION;
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
        if (CategoryList.categories.size() == 0) {
            System.out.println(NO_CATEGORIES_TO_VIEW);
            return;
        }
        int count = 1;
        for (Category category : CategoryList.categories) {
            System.out.println(count + DOT + category.getName());
            count++;
            // print all the events in the category
            for (Event event : category.events) {
                System.out.println(event.toString());
            }
        }
        assert count > 1 : COUNT_ASSERTION;
    }

}

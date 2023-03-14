package seedu.moneymind.Command;

import seedu.moneymind.Category;
import seedu.moneymind.CategoryList;
import seedu.moneymind.Event;
import seedu.moneymind.Ui;

/**
 * ViewCommand class to view the categories and events.
 */
public class ViewCommand implements Command {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String DOT = ".";
    private String categoryName;

    private final boolean isCategorySpecified;

    /**
     * Constructs a new ViewCommand object and views the category.
     *
     * @param categoryName the name of the category
     */
    public ViewCommand(String categoryName) {
        this.categoryName = categoryName;
        this.isCategorySpecified = true;
    }

    /**
     * Constructs a new ViewCommand object and views all the categories.
     */
    public ViewCommand() {
        this.isCategorySpecified = false;
    }

    /**
     * Views a single category.
     */
    private void viewOne() {
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
            for (Event event : category.getEvents()) {
                System.out.println(event.toString());
            }
        }
    }

    @Override
    public void execute(Ui ui) {
        if (isCategorySpecified) {
            viewOne();
        } else {
            viewAll();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

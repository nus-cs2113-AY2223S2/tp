package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.event.Event;
import seedu.moneymind.ui.Ui;

import static seedu.moneymind.string.Strings.NULL_CATEGORY_ASSERTION;
import static seedu.moneymind.string.Strings.NO_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.NULL_CATEGORY_LIST_ASSERTION;
import static seedu.moneymind.string.Strings.NO_CATEGORIES_TO_VIEW;
import static seedu.moneymind.string.Strings.DOT;
import static seedu.moneymind.string.Strings.SHOW_CATEGORY_MESSAGE;

/**
 * Views the categories and events.
 */
public class ViewCommand implements Command {

    private String categoryName;
    private final boolean isCategorySpecified;

    /**
     * Constructs a new ViewCommand object and views a single category.
     *
     * @param categoryName the name of the category
     */
    public ViewCommand(String categoryName) {
        this.categoryName = categoryName;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        this.isCategorySpecified = true;
    }

    /**
     * Constructs a new ViewCommand object and views all the categories.
     */
    public ViewCommand() {
        assert CategoryList.categories != null : NULL_CATEGORY_LIST_ASSERTION;
        this.isCategorySpecified = false;
    }

    /**
     * Views all events in a single category.
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
        if (CategoryList.categories.size() == 0) {
            System.out.println(NO_CATEGORIES_TO_VIEW);
            return;
        }
        System.out.println(SHOW_CATEGORY_MESSAGE);
        int cat_count = 1;
        for (Category category : CategoryList.categories) {
            System.out.println(cat_count + ") Category: " + category.getName() + " (budget: " + category.getBudget() + ")");
            cat_count++;
            int count = 1;
            // print all the events in the category with index
            for (Event event : category.getEvents()) {
                System.out.println("   " + count + DOT + event.toString());
                count++;
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

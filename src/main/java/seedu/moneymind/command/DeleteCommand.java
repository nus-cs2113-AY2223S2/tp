package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.ui.Ui;

import static seedu.moneymind.string.Strings.NULL_CATEGORY_ASSERTION;
import static seedu.moneymind.string.Strings.NO_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.NON_EXISTENT_EVENT;
import static seedu.moneymind.string.Strings.EVENT_DELETION_MESSAGE;
import static seedu.moneymind.string.Strings.CATEGORY_DELETION_MESSAGE;

/**
 * Represents the command to delete an event or a category.
 */
public class DeleteCommand implements Command {

    private final String categoryName;
    private int eventIndex;
    private final boolean isEvent;

    /**
     * Constructs a new DeleteCommand object and deletes the event.
     *
     * @param categoryName the name of the category
     * @param eventIndex the index of the event
     */
    public DeleteCommand(String categoryName, int eventIndex) {
        this.categoryName = categoryName;
        this.eventIndex = eventIndex;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        this.isEvent = true;
    }

    /**
     * Constructs a new DeleteCommand object and deletes the category.
     *
     * @param categoryName the name of the category
     */
    public DeleteCommand(String categoryName) {
        this.categoryName = categoryName;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        this.isEvent = false;
    }

    /**
     * Deletes the event.
     */
    private void deleteEvent() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            return;
        }
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        if (eventIndex >= category.getEvents().size()) {
            System.out.println(NON_EXISTENT_EVENT);
            return;
        }
        String eventName = category.getEvents().get(eventIndex).getDescription();
        category.getEvents().remove(eventIndex);
        System.out.println(EVENT_DELETION_MESSAGE + eventName);
    }

    /**
     * Deletes the category.
     */
    private void deleteCategory() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            return;
        }
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        CategoryList.categories.remove(categoryIndex);
        CategoryCommand.categoryMap.remove(categoryName);
        // loop through the hashmap and update the index of the categories
        for (String key : CategoryCommand.categoryMap.keySet()) {
            if (CategoryCommand.categoryMap.get(key) > categoryIndex) {
                CategoryCommand.categoryMap.put(key, CategoryCommand.categoryMap.get(key) - 1);
            }
        }
        System.out.println(CATEGORY_DELETION_MESSAGE + categoryName);
    }

    @Override
    public void execute(Ui ui) {
        if (isEvent) {
            deleteEvent();
        } else {
            deleteCategory();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

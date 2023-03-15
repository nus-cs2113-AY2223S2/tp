package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.ui.Ui;

/**
 * Represents the command to delete an event or a category.
 */
public class DeleteCommand implements Command {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String EVENT_DELETION_MESSAGE = "Event deleted: ";
    public static final String CATEGORY_DELETION_MESSAGE = "Category deleted: ";
    public static final String NON_EXISTENT_EVENT = "Event does not exist";
    public static final String NULL_CATEGORY_ASSERTION = "Category name should not be null";
    public static final String NULL_EVENT_ASSERTION = "Event name should not be null";
    private String categoryName;
    private String eventName;
    private boolean isEvent;

    /**
     * Constructs a new DeleteCommand object and deletes the event.
     *
     * @param categoryName the name of the category
     * @param eventName    the name of the event
     */
    public DeleteCommand(String categoryName, String eventName) {
        this.categoryName = categoryName;
        this.eventName = eventName;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        assert eventName != null : NULL_EVENT_ASSERTION;
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
        boolean isEventDeleted = false;
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            return;
        }
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        int eventIndex = 0;
        while (eventIndex < category.getEvents().size()) {
            if (category.getEvents().get(eventIndex).getDescription().equals(eventName)) {
                category.getEvents().remove(eventIndex);
                System.out.println(EVENT_DELETION_MESSAGE + eventName);
                isEventDeleted = true;
                --eventIndex;
            }
            ++eventIndex;
        }
        if (!isEventDeleted) {
            System.out.println(NON_EXISTENT_EVENT);
        }
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

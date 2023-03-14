package seedu.moneymind.Command;

import seedu.moneymind.Category;
import seedu.moneymind.CategoryList;
import seedu.moneymind.Ui;

/**
 * Represents the command to delete an event or a category.
 */
public class DeleteCommand implements Command {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String EVENT_DELETION_MESSAGE = "Event deleted: ";
    public static final String CATEGORY_DELETION_MESSAGE = "Category deleted: ";
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
        this.isEvent = true;
    }

    /**
     * Constructs a new DeleteCommand object and deletes the category.
     *
     * @param categoryName the name of the category
     */
    public DeleteCommand(String categoryName) {
        this.categoryName = categoryName;
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
        for (int i = 0; i < category.getEvents().size(); i++) {
            if (category.getEvents().get(i).getDescription().equals(eventName)) {
                category.getEvents().remove(i);
                System.out.println(EVENT_DELETION_MESSAGE + eventName);
            }
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

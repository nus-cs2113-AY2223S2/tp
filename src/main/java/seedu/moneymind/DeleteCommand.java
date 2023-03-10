package seedu.moneymind;

/**
 * Represents the command to delete an event or a category.
 */
public class DeleteCommand {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String EVENT_DELETION_MESSAGE = "Event deleted: ";
    public static final String CATEGORY_DELETION_MESSAGE = "Category deleted: ";
    private String categoryName;
    private String eventName;

    /**
     * Constructs a new DeleteCommand object and deletes the event.
     *
     * @param categoryName the name of the category
     * @param eventName    the name of the event
     */
    public DeleteCommand(String categoryName, String eventName) {
        this.categoryName = categoryName;
        this.eventName = eventName;
        deleteEvent();
    }

    /**
     * Constructs a new DeleteCommand object and deletes the category.
     *
     * @param categoryName the name of the category
     */
    public DeleteCommand(String categoryName) {
        this.categoryName = categoryName;
        deleteCategory();
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
        for (int i = 0; i < category.events.size(); i++) {
            if (category.events.get(i).getDescription().equals(eventName)) {
                category.events.remove(i);
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
}

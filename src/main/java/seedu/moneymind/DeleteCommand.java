package seedu.moneymind;

/**
 * Represents the command to delete an event or a category.
 */
public class DeleteCommand {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String EVENT_DELETION_MESSAGE = "Event deleted: ";
    public static final String CATEGORY_DELETION_MESSAGE = "Category deleted: ";
    public static final String NON_EXISTENT_EVENT = "Event does not exist";
    public static final String NULL_CATEGORY_ASSERTION = "Category name should not be null";
    public static final String NULL_EVENT_ASSERTION = "Event name should not be null";
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
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        assert eventName != null : NULL_EVENT_ASSERTION;
        deleteEvent();
    }

    /**
     * Constructs a new DeleteCommand object and deletes the category.
     *
     * @param categoryName the name of the category
     */
    public DeleteCommand(String categoryName) {
        this.categoryName = categoryName;
        assert categoryName != null : NULL_CATEGORY_ASSERTION;
        deleteCategory();
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
        for (int i = 0; i < category.events.size(); i++) {
            if (category.events.get(i).getDescription().equals(eventName)) {
                category.events.remove(i);
                System.out.println(EVENT_DELETION_MESSAGE + eventName);
                isEventDeleted = true;
            }
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
        System.out.println(CATEGORY_DELETION_MESSAGE + categoryName);
    }
}

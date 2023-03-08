package seedu.duke;

public class DeleteCommand {
    private String categoryName;
    private String eventName;

    public DeleteCommand(String categoryName, String eventName) {
        this.categoryName = categoryName;
        this.eventName = eventName;
        deleteEvent();
    }

    public DeleteCommand(String categoryName) {
        this.categoryName = categoryName;
        deleteCategory();
    }

    // delete the event
    private void deleteEvent() {
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        for (int i = 0; i < category.events.size(); i++) {
            if (category.events.get(i).getDescription().equals(eventName)) {
                category.events.remove(i);
            }
        }
    }

    // delete the category
    private void deleteCategory() {
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        CategoryList.categories.remove(categoryIndex);
    }
}

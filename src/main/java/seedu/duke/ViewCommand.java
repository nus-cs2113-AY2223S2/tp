package seedu.duke;

public class ViewCommand {
    private String categoryName;

    public ViewCommand(String categoryName) {
        this.categoryName = categoryName;
        viewCategory();
    }
    public ViewCommand() {
        viewAll();
    }

    // view the category
    private void viewCategory() {
        int categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        category.viewEventList();
    }

    // view all the categories
    private void viewAll() {
        int count = 1;
        for (Category category : CategoryList.categories) {
            System.out.println(count + "." + category.getName());
            count++;
            // print all the events in the category
            for (Event event : category.events) {
                System.out.println(event.toString());
            }
        }
    }

}

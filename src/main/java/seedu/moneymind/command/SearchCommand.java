package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;
import seedu.moneymind.ui.Ui;

import java.util.ArrayList;

import static seedu.moneymind.category.CategoryList.categories;
import static seedu.moneymind.string.Strings.DOT;
import static seedu.moneymind.string.Strings.NO_SEARCH_RESULTS;

public class SearchCommand implements Command{
    private final String word;

    public SearchCommand(String word) {
        this.word = word;
    }

    //@@author mingyuannus
    @Override
    public void execute(Ui ui) {
        boolean hasMatch = false;
        for (Category category : categories) {
            boolean isMatch = false;
            ArrayList<Event> matchedEvents = new ArrayList<>();
            if (category.getName().contains(word)) {
                isMatch = true;
                hasMatch = true;
            }
            for (Event event : category.events) {
                if (event.getDescription().contains(word)) {
                    matchedEvents.add(event);
                    hasMatch = true;
                }
            }
            if (isMatch || matchedEvents.size() != 0) {
                System.out.println("Category: " + category.getName() + " (budget: " + category.getBudget() + ")");
                int count = 1;
                for (Event event : matchedEvents) {
                    System.out.println(count + DOT + event.toString());
                    ++count;
                }
            }
        }
        if (!hasMatch) {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

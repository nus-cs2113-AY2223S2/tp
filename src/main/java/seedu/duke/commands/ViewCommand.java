package seedu.duke.commands;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.ui.UI;

import java.util.List;


public class ViewCommand extends Command {
    UI ui = new UI();
    private final int numberOfEntriesToView;
    private final Category categoryToView;

    public ViewCommand(int numberOfEntriesToView) {
        this(numberOfEntriesToView, null);
    }

    public ViewCommand(int numberOfEntriesToView, Category categoryToView) {
        this.categoryToView = categoryToView;
        this.numberOfEntriesToView = numberOfEntriesToView;
    }

    /**
     * This method is called in execute method to improve code readability.
     *
     * @param originalEntries list containing all entries in the desired category
     * @return trimmed list containing the latest "N" number of entries, where N is specified in the view command
     */
    private List<Entry> getLatestEntries(List<Entry> originalEntries) {
        int startIndex = numberOfEntriesToView >= originalEntries.size()
                ? 0
                : originalEntries.size() - numberOfEntriesToView;
        int endIndex = originalEntries.size();
        return originalEntries.subList(startIndex, endIndex);
    }

    @Override
    public void execute(EntryLog entries) {
        EntryLog filteredEntries = entries;
        if (categoryToView != null) {
            filteredEntries = entries.filterCategory(categoryToView);
        }

        List<Entry> relevantEntries = filteredEntries.getEntries();
        if (relevantEntries.size() > numberOfEntriesToView) {
            relevantEntries = getLatestEntries(relevantEntries);
        }

        if (categoryToView != null) {
            ui.printEntriesToBeViewed(relevantEntries, categoryToView.toString());
        } else {
            ui.printEntriesToBeViewed(relevantEntries);
        }
    }
}

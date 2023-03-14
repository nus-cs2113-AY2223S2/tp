package seedu.pocketpal.commands;

import seedu.pocketpal.entries.Category;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.ui.UI;

import java.util.List;


public class ViewCommand extends Command {
    private final int numberOfEntriesToView;
    private final Category categoryToView;

    public ViewCommand(int numberOfEntriesToView) {
        this(numberOfEntriesToView, null);
    }

    public ViewCommand(int numberOfEntriesToView, Category categoryToView) {
        this.categoryToView = categoryToView;
        this.numberOfEntriesToView = numberOfEntriesToView;
    }

    @Override
    public void executor(EntryLog entries, UI ui) {
        EntryLog filteredEntries = entries;
        if (categoryToView != null) {
            filteredEntries = entries.filterByCategory(categoryToView);
        }

        List<Entry> relevantEntries = filteredEntries.getEntriesList();
        if (relevantEntries.size() > numberOfEntriesToView) {
            relevantEntries = getLatestEntries(relevantEntries);
        }

        if (categoryToView != null) {
            ui.printEntriesToBeViewed(relevantEntries, categoryToView.toString());
        } else {
            ui.printEntriesToBeViewed(relevantEntries);
        }
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
}

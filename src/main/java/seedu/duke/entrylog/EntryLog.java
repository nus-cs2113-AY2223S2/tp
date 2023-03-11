package seedu.duke.entrylog;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryLog {
    private final List<Entry> entries;

    public EntryLog() {
        this.entries = new ArrayList<>();
    }

    public EntryLog(List<Entry> entries) {
        this.entries = entries;
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    public void delete(int entryId) {
        entries.remove(entryId);
    }

    /**
     * Filter entries by category.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param category Category to be filtered
     * @return EntryLog containing entries matching category
     */
    public EntryLog filterCategory(Category category) {
        List<Entry> filteredEntries = entries
                .stream()
                .filter((entry -> entry.getCategory() == category))
                .collect(Collectors.toList());
        return new EntryLog(filteredEntries);
    }

    /**
     * Filter entries by description or category of entries. Regular expressions are supported.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param query Regex to be filtered (case-insensitive)
     * @return EntryLog containing entries matching query
     */
    public EntryLog filterByQuery(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        List<Entry> filteredEntries = entries
                .stream()
                .filter(entry -> {
                    Matcher descriptionMatcher = pattern.matcher(entry.getDescription());
                    Matcher categoryMatcher = pattern.matcher(entry.getCategoryString());
                    boolean isMatch = descriptionMatcher.find() || categoryMatcher.find();
                    return isMatch;
                })
                .collect(Collectors.toList());
        return new EntryLog(filteredEntries);
    }

    /**
     * Filter items by amount.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param minAmount Minimum amount of entry
     * @param maxAmount Maximum amount of entry
     * @return EntryLog containing entries within range of given amount
     */
    public EntryLog filterAmount(double minAmount, double maxAmount) {
        List<Entry> filteredEntries = entries
                .stream()
                .filter((entry -> {
                    boolean isBelowMin = Double.compare(entry.getAmount(), minAmount) < 0;
                    boolean isAboveMax = Double.compare(entry.getAmount(), maxAmount) > 0;
                    boolean isWithinRange = !isBelowMin && !isAboveMax;
                    return isWithinRange;
                }))
                .collect(Collectors.toList());
        return new EntryLog(filteredEntries);
    }

    public int getSize() {
        return entries.size();
    }

    public List<Entry> getEntries() {
        return entries;
    }
}

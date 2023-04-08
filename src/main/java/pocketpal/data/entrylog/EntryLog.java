package pocketpal.data.entrylog;

import pocketpal.communication.Serialisable;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.data.parsing.EntryLogParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryLog implements Serialisable {
    private static final Logger logger = Logger.getLogger(EntryLog.class.getName());
    private final List<Entry> entries;

    public EntryLog() {
        this(new ArrayList<>());
    }

    public EntryLog(List<Entry> entries) {
        assert entries != null : "List cannot be null when instantiating EntryLog";
        this.entries = entries;
        sort();
    }

    /**
     * Add an entry to the log.
     *
     * @param entry Entry to be added
     */
    public void addEntry(Entry entry) {
        assert entry != null : "Entry cannot be null when adding to EntryLog";
        logger.info("Adding entry: " + entry.getDescription());
        entries.add(entry);
    }

    /**
     * Delete all entries.
     */
    public void clearAllEntries() {
        entries.clear();
    }

    /**
     * Delete an entry from the log. Should only be called in the main log.
     *
     * @param entryId Id corresponding to the index (0-based)
     */
    public Entry deleteEntry(int entryId) throws IndexOutOfBoundsException {
        logger.info("Deleting entry: " + entryId);
        Entry target = entries.get(entryId);
        entries.remove(entryId);
        return target;
    }

    /**
     * Compare to another EntryLog. Ignores DateTime in both entries.
     *
     * @param entries EntryLog to compare
     * @return true if both have the same entries, false otherwise
     */
    public boolean equals(EntryLog entries) {
        if (getSize() != entries.getSize()) {
            return false;
        }
        for (int i = 1; i <= getSize(); ++i) {
            if (!getEntry(i).equals(entries.getEntry(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filter items by amount.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param minAmount Minimum amount of entry
     * @param maxAmount Maximum amount of entry
     * @return EntryLog containing entries within range of given amount
     */
    public EntryLog filterByAmount(double minAmount, double maxAmount) {
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

    /**
     * Filter entries by description or category of entries. Regular expressions are supported.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param query Regex to be filtered (case-insensitive)
     * @return EntryLog containing entries matching query
     */
    public EntryLog filterByQuery(String query) {
        assert query != null;
        if (query.isEmpty()) {
            logger.info("User entered empty query, returning all entries.");
            return this; // return all entries
        }
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
     * Filter entries by category.
     * Can be chained with other filter* methods to filter multiple entries.
     *
     * @param category Category to be filtered
     * @return EntryLog containing entries matching category
     */
    public EntryLog filterByCategory(Category category) {
        assert category != null;
        List<Entry> filteredEntries = entries
                .stream()
                .filter((entry -> entry.getCategory() == category))
                .collect(Collectors.toList());
        return new EntryLog(filteredEntries);
    }

    public EntryLog filterBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        assert startDateTime != null && endDateTime != null;
        List<Entry> filteredEntries = entries
                .stream()
                .filter((entry -> startDateTime.isBefore(entry.getDateTime()) &&
                        endDateTime.isAfter(entry.getDateTime())))
                .collect(Collectors.toList());
        return new EntryLog(filteredEntries);
    }

    /**
     * Access an entry in the log using its id.
     *
     * @param entryId id corresponding to the index (1-based)
     */
    public Entry getEntry(int entryId) {
        try {
            return entries.get(entryId - 1);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Attempted to access an invalid entry index: " + entryId, e);
            return null;
        }
    }


    /**
     * Get the raw List object containing entries entered by the user.
     * There should not be a need to use this method apart from saving and testing data.
     *
     * @return List containing all entries in the EntryLog
     */
    public List<Entry> getEntriesList() {
        return entries;
    }

    /**
     * This method is called in execute method to improve code readability.
     *
     * @param numEntries The number of recent entries to view
     * @return trimmed list containing the latest "N" number of entries, where N is specified in the view command
     */
    public EntryLog getLatestEntries(int numEntries) {
        int startIndex = numEntries >= getSize()
                ? 0
                : getSize() - numEntries;
        int endIndex = getSize();
        return new EntryLog(entries.subList(startIndex, endIndex));
    }

    public int getSize() {
        return entries.size();
    }

    /**
     * This method returns the sum of the prices of all entries,
     * excluding those of the income category.
     */
    public double getTotalExpenditure(){
        double totalExpenditure = 0;
        for(int index = 1; index <= getSize(); index++){
            if(getEntry(index).getCategory() != Category.INCOME) {
                totalExpenditure += getEntry(index).getAmount();
            }
        }
        return totalExpenditure;
    }
    /**
    * This method returns the sum of the prices of all entries
    * of the income category.
    */
    public double getTotalIncome(){
        double totalIncome = 0;
        for(int index = 1; index <= getSize(); index++){
            if(getEntry(index).getCategory() == Category.INCOME) {
                totalIncome += getEntry(index).getAmount();
            }
        }
        return totalIncome;
    }

    @Override
    public String serialise() {
        return EntryLogParser.serialise(this);
    }

    /**
     * Sorts all entries in ascending date order.
     */
    private void sort() {
        logger.info("Sorting entries by ascending date");
        entries.sort(Comparator.comparing(Entry::getDateTime));
    }
}

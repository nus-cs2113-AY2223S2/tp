package seedu.pocketpal.entrylog;

import seedu.pocketpal.constants.MessageConstants;
import seedu.pocketpal.entries.Category;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.exceptions.InvalidArgumentsException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryLog {
    private static final Logger logger = Logger.getLogger(EntryLog.class.getName());
    private final List<Entry> entries;

    public EntryLog() {
        this.entries = new ArrayList<>();
    }

    public EntryLog(List<Entry> entries) {
        assert entries != null : "List cannot be null when instantiating EntryLog";
        this.entries = entries;
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
     * Delete an entry from the log. Should only be called in the main log.
     *
     * @param entryId Id corresponding to the index (0-based)
     * @throws InvalidArgumentsException If an invalid index is passed
     */
    public Entry deleteEntry(int entryId) throws InvalidArgumentsException {
        try {
            logger.info("Deleting entry: " + entryId);
            Entry target = entries.get(entryId);
            entries.remove(entryId);
            return target;
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Attempted to delete an invalid entry index: " + entryId, e);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
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

    /**
     * Access an entry in the log using its id.
     *
     * @param entryId Id corresponding to the index (0-based)
     * @throws InvalidArgumentsException If an invalid index is passed
     */
    public Entry getEntry(int entryId) throws InvalidArgumentsException {
        try {
            return entries.get(entryId);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Attempted to access an invalid entry index: " + entryId, e);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
    }

    public List<Entry> getEntriesList() {
        return entries;
    }

    public int getSize() {
        return entries.size();
    }
}

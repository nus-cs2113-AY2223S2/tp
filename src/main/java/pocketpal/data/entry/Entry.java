package pocketpal.data.entry;

import pocketpal.data.parsing.EntryParser;
import pocketpal.communication.Serialisable;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.frontend.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * Represents an entry, from which various possible entry types
 * such as Food, Medical or Income are derived. User may specify
 * their desired entry type when adding to their entry log.
 * e.g., <code>/add -d lunch -p 15 -c food</code>
 */
public class Entry implements Serialisable {
    private static final Logger logger = Logger.getLogger(Entry.class.getName());
    private Category category;
    private String description;
    private double amount;
    private final LocalDateTime dateTime;

    public Entry(String description, double amount, Category category) {
        this(description, amount, category, LocalDateTime.now());
    }

    public Entry(String description, double amount, Category category, LocalDateTime dateTime) {
        assert amount >= 0 : "Entry amount must be non-negative!";
        assert category != null : "Entry category cannot be null";
        assert !description.isEmpty() : "Entry description cannot be empty";
        assert dateTime != null : "Entry DateTime cannot be null";
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.dateTime = dateTime;
        logger.info("Initialised new entry " + serialise());
    }

    /**
     * Compare to another entry. Ignores DateTime in both entries.
     *
     * @param entry Entry to compare
     * @return true if both entry is the same as this, false otherwise
     */
    public boolean equals(Entry entry) {
        return equals(entry, false);
    }

    /**
     * Compare to another entry.
     *
     * @param entry       Entry to compare
     * @param compareDate True if date and time in both entries need to be the same
     * @return true if both entry is the same as this, false otherwise
     */
    public boolean equals(Entry entry, boolean compareDate) {
        boolean isSameAmount = amount == entry.getAmount();
        boolean isSameCategory = category == entry.getCategory();
        boolean isSameDescription = description.equals(entry.getDescription());
        boolean isSameDateTime = dateTime.isEqual(entry.getDateTime());
        return isSameAmount && isSameCategory && isSameDescription && (!compareDate || isSameDateTime);
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Converts category into a string.
     * @return category in Title Case
     */
    public String getCategoryString() {
        assert category != null : "Unexpected null category";
        return StringUtil.toTitleCase(String.valueOf(category));
    }

    public String getDateTimeString() {
        return dateTime.format(
            DateTimeFormatter.ofPattern(
                MiscellaneousConstants.DATETIME_PATTERN
            )
        );
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(double amount) {
        assert amount >= 0 : "Entry amount must be non-negative!";
        this.amount = amount;
    }

    public void setCategory(Category category) {
        assert category != null : "Entry category cannot be null";
        this.category = category;
    }

    public void setDescription(String description) {
        assert !description.isEmpty() : "Entry description cannot be empty";
        this.description = description;
    }

    @Override
    public String serialise() {
        return EntryParser.serialise(this);
    }
}

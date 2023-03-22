package chching.record;

import java.time.LocalDate;

/**
 * Models a class for an expense entry
 */
public class Expense extends Record {
    private final String category;
    /**
     * Constructor to instantiate Expense objects
     *
     * @param category      Category of expense
     * @param description       Description of expense
     * @param date      Date of expense
     * @param value     Value of expense
     */
    public Expense(String category, String description, LocalDate date, double value) {
        super(description, date, value);
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Changes String format
     */
    @Override
    public String toString() {
        return "Category - " + getCategory() +
                " | Description - " + getDescription() +
                " | Date - " + getDateString() +
                " | Value - " + String.format("%.02f", getValue());
    }

}

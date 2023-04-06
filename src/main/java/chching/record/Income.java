package chching.record;

import java.time.LocalDate;
/**
 * Models a class for an income entry
 */
public class Income extends Record {
    /**
     * Constructor to instantiate Income objects
     *
     * @param description       Description of income
     * @param date      Date of income
     * @param value     Value of income
     */
    public Income(String description, LocalDate date, double value) {
        super(description, date, value);
    }

    /**
     * Changes String format
     */
    @Override
    public String toString() {
        return  "Description - " + getDescription() +
                " | Date - " + getDateString() +
                " | Value - " + String.format("%.02f", getValue());
    }

    public String getDescription() {
        return super.getDescription();
    }
}

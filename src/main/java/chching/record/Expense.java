package chching.record;

import java.time.LocalDate;

public class Expense extends Record {
    private final String category;

    public Expense(String category, String description, LocalDate date, double value) {
        super(description, date, value);
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return "Category - " + getCategory() +
                " | Description - " + getDescription() +
                " | Date - " + getDateString() +
                " | Value - " + String.format("%.02f", getValue());
    }

}

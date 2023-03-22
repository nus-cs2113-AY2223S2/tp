package chching.record;

import java.time.LocalDate;

public class Income extends Record{
    public Income(String description, LocalDate date, double value) {
        super(description, date, value);
    }

    @Override
    public String toString() {
        return  "Description - " + getDescription() +
                " | Date - " + getDateString() +
                " | Value - " + String.format("%.02f", getValue());
    }
}

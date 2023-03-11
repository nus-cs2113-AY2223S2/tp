package seedu.Expenditure;

import java.time.LocalDate;

public class EntertainmentExpenditure extends Expenditure {
    public EntertainmentExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Entertainment] || %s", super.toString());
    }
}

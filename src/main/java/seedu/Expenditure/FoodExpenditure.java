package seedu.Expenditure;

import java.time.LocalDate;

public class FoodExpenditure extends Expenditure {
    public FoodExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Food] || %s", super.toString());
    }
}

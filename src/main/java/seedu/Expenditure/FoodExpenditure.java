package seedu.Expenditure;

import java.time.LocalDate;

public class FoodExpenditure extends Expenditure {

    public FoodExpenditure(double value, LocalDate date) {
        super(value, date);
    }

    @Override
    public String toString() {
        return getDate() + Double.toString(getValue());
    }
}

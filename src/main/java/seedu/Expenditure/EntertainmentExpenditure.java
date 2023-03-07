package seedu.Expenditure;

import java.time.LocalDate;

public class EntertainmentExpenditure extends Expenditure {
    public EntertainmentExpenditure(double value, LocalDate date) {
        super(value, date);
    }

    @Override
    public String toString() {
        return getDate() + Double.toString(getValue());
    }
}

package seedu.Expenditure;

import java.time.LocalDate;

public class TransportExpenditure extends Expenditure {
    public TransportExpenditure(double value, LocalDate date) {
        super(value, date);
    }

    public String toString() {
        return getDate() + Double.toString(getValue());
    }
}

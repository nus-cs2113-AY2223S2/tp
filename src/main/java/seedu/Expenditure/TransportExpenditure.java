package seedu.expenditure;

import java.time.LocalDate;

public class TransportExpenditure extends Expenditure {
    public TransportExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    public String toString() {
        return String.format("[Transport] || %s", super.toString());
    }
}

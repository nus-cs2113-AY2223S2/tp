package seedu.Expenditure;

import java.time.LocalDate;

public class AccomodationExpenditure extends Expenditure {
    private boolean isPaid;

    public AccomodationExpenditure(double value, LocalDate date) {
        super(value, date);
        isPaid = false;
    }

    public void setPaid() {
        isPaid = true;
    }

    public String getStatusIcon() {
        return (isPaid) ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDate() + getValue();
    }
}

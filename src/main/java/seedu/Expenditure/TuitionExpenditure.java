package seedu.Expenditure;

import java.time.LocalDate;

public class TuitionExpenditure extends Expenditure {
    boolean isPaid;

    public TuitionExpenditure(double value, LocalDate date) {
        super(value, date);
        isPaid = false;
    }

    public void setPaid() {
        isPaid = true;
    }

    public String getPaidIcon() {
        return (isPaid) ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getPaidIcon() + getDate() + getValue();
    }

}

package seedu.expenditure;

import java.time.LocalDate;

public class TuitionExpenditure extends Expenditure {
    boolean isPaid;

    public TuitionExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
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
        return String.format("[Tuition] || %s || %s", getPaidIcon(), super.toString());
    }

}

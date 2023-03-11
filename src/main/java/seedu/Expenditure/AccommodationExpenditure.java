package seedu.expenditure;

import java.time.LocalDate;

public class AccommodationExpenditure extends Expenditure {
    private boolean isPaid;

    public AccommodationExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
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
        return String.format("[Accommodation] || %s || %s", getStatusIcon(), super.toString());
    }
}

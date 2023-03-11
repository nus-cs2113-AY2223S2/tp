package seedu.Expenditure;

import java.time.LocalDate;

public class BorrowExpenditure extends Expenditure {
    private LocalDate deadline;
    private String borrowerName;

    public BorrowExpenditure(String description, String borrowerName, double borrowValue, LocalDate date,
            LocalDate deadline) {
        super(description, borrowValue, date);
        setBorrowerName(borrowerName);
        setDeadline(deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public String toString() {
        return String.format("[Borrow] || %s || %s || %s", getBorrowerName(), super.toString(), getDeadline());
    }
}

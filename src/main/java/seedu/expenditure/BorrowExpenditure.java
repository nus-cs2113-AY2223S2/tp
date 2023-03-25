package seedu.expenditure;

import java.time.LocalDate;

public class BorrowExpenditure extends Expenditure {
    private LocalDate deadline;
    private String borrowerName;
    public static final String expenditureType = "B";

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
        return String.format("[Borrow] || Borrowed from: %s || %s || By: %s",
                getBorrowerName(), super.toString(), getDeadline());
    }

    @Override
    public String getExpenditureType() {
        return expenditureType;
    }

    @Override
    public String saveInfo() {
        return getExpenditureType() +
                "d/" + getDescription() +
                "v/" + getValue() +
                "t/" + getDate() +
                "p/" + "None" +
                "n/" + getBorrowerName() +
                "o/" + getDeadline() + "\n";
    }
}

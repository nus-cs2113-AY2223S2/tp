package seedu.expenditure;

import java.time.LocalDate;

public class LendExpenditure extends Expenditure {
    private LocalDate deadline;
    private String lenderName;

    public LendExpenditure(String description, String lenderName, double lendValue, LocalDate date,
                             LocalDate deadline) {
        super(description, lendValue, date);
        setLenderName(lenderName);
        setDeadline(deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    @Override
    public String toString() {
        return String.format("[Borrow] || %s || %s || %s", getLenderName(), super.toString(), getDeadline());
    }
}

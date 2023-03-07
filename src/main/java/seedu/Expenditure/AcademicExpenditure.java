package seedu.Expenditure;

import java.time.LocalDate;

public class AcademicExpenditure extends Expenditure {
    private String description;

    public AcademicExpenditure(double value, LocalDate date, String description) {
        super(value, date);
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDate() + Double.toString(getValue()) + getDescription();
    }
}

package seedu.Expenditure;

import java.time.LocalDate;

public class AcademicExpenditure extends Expenditure {
    public AcademicExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Academic] || %s", super.toString());
    }

    @Override
    public String getExpenditureType() {
        return "Acad";
    }
}

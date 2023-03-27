package seedu.expenditure;

import java.time.LocalDate;

public class AcademicExpenditure extends Expenditure {
    public static final String expenditureType = "Acad";
    public AcademicExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Academic] || %s", super.toString());
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
                "n/" + "None" +
                "o/" + "None" + "\n";

    }
}

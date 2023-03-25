package seedu.expenditure;

import java.time.LocalDate;

public class OtherExpenditure extends Expenditure {
    public static final String expenditureType = "O";
    public OtherExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Other] || %s", super.toString());
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

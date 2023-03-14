package seedu.expenditure;

import java.time.LocalDate;

public class OtherExpenditure extends Expenditure {
    public OtherExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Other] || %s", super.toString());
    }

    @Override
    public String getExpenditureType() {
        return "O";
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

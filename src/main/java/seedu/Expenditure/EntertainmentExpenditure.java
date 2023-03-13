package seedu.expenditure;

import java.time.LocalDate;

public class EntertainmentExpenditure extends Expenditure {
    public EntertainmentExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    @Override
    public String toString() {
        return String.format("[Entertainment] || %s", super.toString());
    }

    @Override
    public String getExpenditureType() {
        return "En";
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

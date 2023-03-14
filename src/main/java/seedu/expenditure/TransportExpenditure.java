package seedu.expenditure;

import java.time.LocalDate;

public class TransportExpenditure extends Expenditure {
    public TransportExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    public String toString() {
        return String.format("[Transport] || %s", super.toString());
    }

    @Override
    public String getExpenditureType() {
        return "Tr";
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

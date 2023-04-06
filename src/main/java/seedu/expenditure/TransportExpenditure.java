package seedu.expenditure;

import java.time.LocalDate;

public class TransportExpenditure extends Expenditure {
    public static final String EXPENDITURE_TYPE = "Tr";

    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    boolean isPaid;

    public TransportExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public String toString() {
        return String.format("[Transport] || %s", super.toString());
    }

    @Override
    public String expenditureString(String currency) {
        return String.format("[Transport] || %s", super.expenditureString(currency));
    }

    @Override
    public String getExpenditureType() {
        return EXPENDITURE_TYPE;
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

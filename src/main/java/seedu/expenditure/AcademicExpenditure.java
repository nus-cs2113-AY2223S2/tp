package seedu.expenditure;

import java.time.LocalDate;

public class AcademicExpenditure extends Expenditure {
    public static final String EXPENDITURE_TYPE = "Acad";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    boolean isPaid;

    public AcademicExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }
    @Override
    public String toString() {
        return String.format("[Academic] || %s", super.toString());
    }
    @Override
    public String expenditureString(String currency) {
        return String.format("[Academic] || %s", super.expenditureString(currency));
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
                "o/" + "None" +
                "r/" + "None" + "\n";

    }
}

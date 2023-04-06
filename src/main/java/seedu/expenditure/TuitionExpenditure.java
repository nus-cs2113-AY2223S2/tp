package seedu.expenditure;

import java.time.LocalDate;

public class TuitionExpenditure extends Expenditure {

    public static final String EXPENDITURE_TYPE = "Tu";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    
    boolean isPaid;

    public TuitionExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
        isPaid = false;
    }

    public void setPaid() {
        isPaid = true;
    }

    public void resetPaid() {
        isPaid = false;
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    @Override
    public String toString() {
        return String.format("[Tuition] || %s || %s", getPaidIcon(), super.toString());
    }

    @Override
    public String expenditureString(String currency) {
        return String.format("[Tuition] || %s || %s", getPaidIcon(), super.expenditureString(currency));
    }

    public String getExpenditureType() {
        return EXPENDITURE_TYPE;
    }

    @Override
    public String saveInfo() {
        return getExpenditureType() +
                "d/" + getDescription() +
                "v/" + getValue() +
                "t/" + getDate() +
                "p/" + getPaidIcon() +
                "n/" + "None" +
                "o/" + "None" + "\n";
    }
}

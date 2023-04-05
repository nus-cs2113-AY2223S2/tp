package seedu.expenditure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LendExpenditure extends Expenditure {
    public static final String EXPENDITURE_TYPE = "L";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    boolean isPaid;
    private LocalDate deadline;
    private String lenderName;



    public LendExpenditure(String description, String lenderName, double lendValue, LocalDate date,
            LocalDate deadline) {
        super(description, lendValue, date);
        setLenderName(lenderName);
        setDeadline(deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getLenderName() {
        return lenderName;
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public void setLenderNameAndDeadline(String lenderName, LocalDate deadline) {
        this.lenderName = lenderName;
        this.deadline = deadline;
    }

    public String getFullDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getDeadline().format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[Lend] || Lent to: %s || %s || by: %s",
                getLenderName(), super.toString(), getFullDeadline());
    }

    @Override
    public String expenditureString(String currency) {
        return String.format("[Lend] || Lent to: %s || %s || by: %s",
                getLenderName(), super.expenditureString(currency), getFullDeadline());
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
                "n/" + getLenderName() +
                "o/" + getDeadline() + "\n";
    }
}

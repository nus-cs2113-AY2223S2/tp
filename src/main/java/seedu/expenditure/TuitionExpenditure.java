package seedu.expenditure;

import java.time.LocalDate;

public class TuitionExpenditure extends Expenditure {

    public static final String EXPENDITURE_TYPE = "Tu";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    LocalDate nextRepeatDate = super.getDate();
    LocalDate currentDate = LocalDate.now();
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

    public void checkMark() {
        if (currentDate.equals(nextRepeatDate) || currentDate.isAfter(nextRepeatDate)) {
            isPaid = false;
            nextRepeatDate = getNextRepeatDate();
        }
    }

    public LocalDate getNextRepeatDate() {
        String stringNextYear = fetchNextYear();
        String stringNextMonth = fetchNextMonth();
        String stringNextDay = fetchNextDay();
        String newDate = String.format("%s-%s-%s", stringNextYear, stringNextMonth, stringNextDay);
        return LocalDate.parse(newDate);
    }

    public String fetchNextYear() {
        // Repeats annually
        int nextYear = nextRepeatDate.getYear() + 1;
        return Integer.toString(nextYear);
    }

    public String fetchNextMonth() {
        int nextMonth = nextRepeatDate.getMonthValue();
        return Integer.toString(nextMonth);
    }

    public String fetchNextDay() {
        int nextDay = nextRepeatDate.getDayOfMonth();
        return Integer.toString(nextDay);
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

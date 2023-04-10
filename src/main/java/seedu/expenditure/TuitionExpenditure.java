package seedu.expenditure;

import java.time.LocalDate;

public class TuitionExpenditure extends Expenditure {

    public static final String EXPENDITURE_TYPE = "Tu";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    private boolean isPaid;
    private LocalDate repeatDate;

    public TuitionExpenditure(String description, double value, LocalDate date, LocalDate repeatDate) {
        super(description, value, date);
        resetPaid();
        setRepeatDate(repeatDate);
    }

    public void setPaid() {
        isPaid = true;
    }

    public void resetPaid() {
        isPaid = false;
    }

    public void setRepeatDate(LocalDate repeatDate) {
        this.repeatDate = repeatDate;
    }

    public LocalDate getRepeatDate() {
        return repeatDate;
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public void checkMark() {
        checkNextRepeatDate();
        handleNextRepeat();
    }

    public void checkNextRepeatDate() {
        LocalDate firstDate = getDate();
        LocalDate currentDate = LocalDate.now();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            repeatDate = setNextRepeatDate();
        }
    }

    public void handleNextRepeat() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            isPaid = false;
            repeatDate = setNextRepeatDate();
        }
    }

    public LocalDate setNextRepeatDate() {
        String stringNextYear = fetchNextYear();
        String stringNextMonth = fetchMonth();
        String stringNextDay = fetchDay();
        String newDate = String.format("%s-%s-%s", stringNextYear, stringNextMonth, stringNextDay);
        return LocalDate.parse(newDate);
    }

    public String fetchNextYear() {
        // Repeats annually
        final int incrementYear = 1;
        int nextYear = repeatDate.getYear() + incrementYear;
        return Integer.toString(nextYear);
    }

    public String fetchMonth() {
        int nextMonth = repeatDate.getMonthValue();
        final int doubleDigitMonth = 10;
        if (nextMonth < doubleDigitMonth) {
            return  "0" + nextMonth;
        } else {
            return Integer.toString(nextMonth);
        }
    }

    public String fetchDay() {
        int nextDay = repeatDate.getDayOfMonth();
        final int doubleDigitDay = 10;
        if (nextDay < doubleDigitDay) {
            return "0" + nextDay;
        }
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
                "o/" + "None" +
                "r/" + repeatDate + "\n";
    }
}

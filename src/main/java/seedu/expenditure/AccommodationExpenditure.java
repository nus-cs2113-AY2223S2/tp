package seedu.expenditure;

import java.time.LocalDate;

public class AccommodationExpenditure extends Expenditure {
    public static final String EXPENDITURE_TYPE = "Accom";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    private boolean isPaid;
    private LocalDate nextRepeatDate;

    public AccommodationExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
        isPaid = false;
        nextRepeatDate = date;
    }

    public void setPaid() {
        isPaid = true;
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public void resetPaid() {
        isPaid = false;
    }

    public String getStatusIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public void checkMark() {
        LocalDate currentDate = LocalDate.now();
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
        if (nextMonth < 10) {
            return  "0" + nextMonth;
        } else {
            return Integer.toString(nextMonth);
        }
    }

    public String fetchNextDay() {
        int nextDay = nextRepeatDate.getDayOfMonth();
        if (nextDay < 10) {
            return "0" + nextDay;
        }
        return Integer.toString(nextDay);
    }


    @Override
    public String toString() {
        return String.format("[Accommodation] || %s || %s", getStatusIcon(), super.toString());
    }
    @Override
    public String expenditureString(String currency) {
        return String.format("[Accommodation] || %s || %s", getStatusIcon(), super.expenditureString(currency));
    }

    @Override
    public String getExpenditureType() {
        return EXPENDITURE_TYPE;
    }

    @Override
    public String saveInfo() { // NOT DONE
        return getExpenditureType() +
                "d/" + getDescription() +
                "v/" + getValue() +
                "t/" + getDate() +
                "p/" + getStatusIcon() +
                "n/" + "None" +
                "o/" + "None" + "\n";
    }
}

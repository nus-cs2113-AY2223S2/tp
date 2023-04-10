package seedu.exceptions;

import java.time.LocalDate;

import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.Expenditure;
import seedu.expenditure.TuitionExpenditure;
import seedu.parser.ParseIndividualValue;

public class ExceptionChecker {
    public static final int TWO_DECIMAL_PLACE = 2;
    public static final String START_DATE = "2000-01-01";
    public static final double START_YEAR = 2000;

    public static void checkEmptyString(String string) throws EmptyStringException {
        if (string.isEmpty()) {
            throw new EmptyStringException();
        }
    }

    public static void checkValidAmount(double amountVal) throws NotPositiveValueException, SmallAmountException {
        if (amountVal <= 0) {
            throw new NotPositiveValueException();
        } else if (amountVal < 0.01) {
            throw new SmallAmountException();
        }
    }

    public static void checkValidDoubleInput(String amountVal) throws InvalidCharacterInAmount {
        String lowerCaseAmountVal = amountVal.toLowerCase();
        boolean containsSpecialCharacter = (lowerCaseAmountVal.contains("f") || lowerCaseAmountVal.contains("d") ||
                lowerCaseAmountVal.contains("e"));
        if (containsSpecialCharacter) {
            throw new InvalidCharacterInAmount();
        }
    }

    public static void check(String amountVal) throws InvalidCharacterInAmount {
        String lowerCaseAmountVal = amountVal.toLowerCase();
        boolean containsSpecialCharacter = (lowerCaseAmountVal.contains("f") || lowerCaseAmountVal.contains("d") ||
                lowerCaseAmountVal.contains("e"));
        if (containsSpecialCharacter) {
            throw new InvalidCharacterInAmount();
        }
    }

    public static void checkDate(LocalDate startDate, LocalDate endDate)
            throws InvalidDateException, InvalidDeadlineException {
        LocalDate currentDate = LocalDate.now();
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateException();
        }
        if (endDate.isBefore(currentDate)) {
            throw new InvalidDeadlineException();
        }
    }

    public static void checkDateLimit(LocalDate currentDate)
            throws DateLimitException {
        LocalDate firstdate = LocalDate.parse(START_DATE);
        if (currentDate.isBefore(firstdate)) {
            throw new DateLimitException();
        }
    }

    public static void checkYearLimit(String currentDate)
            throws YearLimitException {
        if (Double.parseDouble(currentDate) < START_YEAR) {
            throw new YearLimitException();
        }
    }

    public static void checkAlreadyMark(Expenditure expenditure) throws AlreadyMarkException {
        if (expenditure instanceof TuitionExpenditure) {
            boolean isAlreadyPaidTuition = ((TuitionExpenditure) expenditure).getPaidIcon()
                    .equals(TuitionExpenditure.iconPaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyMarkException();
            }
        } else if (expenditure instanceof AccommodationExpenditure) {
            boolean isAlreadyPaidTuition = ((AccommodationExpenditure) expenditure).getStatusIcon()
                    .equals(TuitionExpenditure.iconPaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyMarkException();
            }
        }
    }

    public static void checkAlreadyUnmark(Expenditure expenditure) throws AlreadyUnmarkException {
        if (expenditure instanceof TuitionExpenditure) {
            boolean isAlreadyPaidTuition = ((TuitionExpenditure) expenditure).getPaidIcon()
                    .equals(TuitionExpenditure.iconUnpaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyUnmarkException();
            }
        } else if (expenditure instanceof AccommodationExpenditure) {
            boolean isAlreadyPaidTuition = ((AccommodationExpenditure) expenditure).getStatusIcon()
                    .equals(TuitionExpenditure.iconUnpaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyUnmarkException();
            }
        }
    }
    public static void checkIfMoreThanTwoDecimalPlaces(String userInput, String dot, String blank)
            throws WrongPrecisionException, EmptyStringException {
        if (userInput.contains(dot)) {
            String twoDecimalPlaces = ParseIndividualValue.parseIndividualValue(userInput,dot,blank);
            if (twoDecimalPlaces.length() > TWO_DECIMAL_PLACE) {
                throw new WrongPrecisionException();
            }
        }
    }

    public static void checkLargeValue(Double value) throws LargeValueException {
        if (value >= 10000000d) {
            throw new LargeValueException();
        }
    }
}

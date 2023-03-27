package seedu.exceptions;

import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.Expenditure;
import seedu.expenditure.TuitionExpenditure;

public class ExceptionChecker {
    public static void checkEmptyString(String string) throws EmptyStringException {
        if (string.isEmpty()) {
            throw new EmptyStringException();
        }
    }

    public static void checkAlreadyMark(Expenditure expenditure) throws AlreadyMarkException {
        if (expenditure instanceof TuitionExpenditure) {
            boolean isAlreadyPaidTuition = ((TuitionExpenditure) expenditure).
                    getPaidIcon().equals(TuitionExpenditure.iconPaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyMarkException();
            }
        } else if (expenditure instanceof AccommodationExpenditure) {
            boolean isAlreadyPaidTuition = ((AccommodationExpenditure) expenditure).
                    getStatusIcon().equals(TuitionExpenditure.iconPaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyMarkException();
            }
        }
    }

    public static void checkAlreadyUnmark(Expenditure expenditure) throws AlreadyUnmarkException {
        if (expenditure instanceof TuitionExpenditure) {
            boolean isAlreadyPaidTuition = ((TuitionExpenditure) expenditure).
                    getPaidIcon().equals(TuitionExpenditure.iconUnpaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyUnmarkException();
            }
        } else if (expenditure instanceof AccommodationExpenditure) {
            boolean isAlreadyPaidTuition = ((AccommodationExpenditure) expenditure).
                    getStatusIcon().equals(TuitionExpenditure.iconUnpaid);
            if (isAlreadyPaidTuition) {
                throw new AlreadyUnmarkException();
            }
        }
    }
}


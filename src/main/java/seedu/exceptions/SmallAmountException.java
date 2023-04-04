package seedu.exceptions;

public class SmallAmountException extends Exception {
    @Override
    public String getMessage() {
        return "The amount you have entered is under 0.01! MyLedger accepts values from 1 cent and above.";
    }
}

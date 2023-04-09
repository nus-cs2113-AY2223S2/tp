package seedu.brokeMan.exception;

public class DateSmallerThanMinimumException extends BrokeManException {

    @Override
    public String getMessage() {
        return "Date entered is before earliest date available, which is 2000/01";
    }
}

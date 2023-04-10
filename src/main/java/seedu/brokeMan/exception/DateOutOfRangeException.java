package seedu.brokeMan.exception;

public class DateOutOfRangeException extends BrokeManException {

    @Override
    public String getMessage() {
        return "Date entered beyond the available date range, which is from year 2000 to 9999";
    }
}

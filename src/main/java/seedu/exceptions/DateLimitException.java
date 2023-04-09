package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_DATE_LIMIT_MESSAGE;

public class DateLimitException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_DATE_LIMIT_MESSAGE.toString();
    }
}
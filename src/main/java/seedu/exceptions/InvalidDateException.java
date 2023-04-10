package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_INVALID_DATE_MESSAGE;

public class InvalidDateException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_INVALID_DATE_MESSAGE.toString();
    }
}

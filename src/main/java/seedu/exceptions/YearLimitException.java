package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_YEAR_LIMIT_MESSAGE;

public class YearLimitException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_YEAR_LIMIT_MESSAGE.toString();
    }
}

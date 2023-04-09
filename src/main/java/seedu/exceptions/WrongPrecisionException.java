package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_INVALID_AMOUNT_PRECISION;

public class WrongPrecisionException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_INVALID_AMOUNT_PRECISION.toString();
    }
}

package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_NO_PAID_FIELD_MESSAGE;

public class NoPaidFieldException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_NO_PAID_FIELD_MESSAGE.toString();
    }
}

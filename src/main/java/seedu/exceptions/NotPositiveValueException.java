package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_NOT_POSITIVE_VALUE_MESSAGE;;

public class NotPositiveValueException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString();
    }
}

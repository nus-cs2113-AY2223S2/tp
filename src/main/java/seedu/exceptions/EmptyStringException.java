package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_EMPTY_STRING_MESSAGE;

public class EmptyStringException extends Exception {
    @Override
    public String getMessage() {
        return  ERROR_EMPTY_STRING_MESSAGE.toString();
    }
}

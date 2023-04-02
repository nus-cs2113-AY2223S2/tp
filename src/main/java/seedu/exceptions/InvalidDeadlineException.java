package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_INVALID_DEADLINE_MESSAGE;

public class InvalidDeadlineException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_INVALID_DEADLINE_MESSAGE.toString();
    }
}


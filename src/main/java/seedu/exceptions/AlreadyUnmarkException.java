package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_UNMARK_MESSAGE;

public class AlreadyUnmarkException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_UNMARK_MESSAGE.toString();
    }
}

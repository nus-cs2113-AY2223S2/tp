package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_MARK_MESSAGE;

public class AlreadyMarkException extends Exception {

    @Override
    public String getMessage() {
        return ERROR_MARK_MESSAGE.toString();
    }

}


package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_AMOUNT_FORMAT_MESSAGE;

public class InvalidCharacterInAmount extends Exception {
    @Override
    public String getMessage() {
        return ERROR_AMOUNT_FORMAT_MESSAGE.toString();
    }
}

package seedu.exceptions;

import static seedu.ui.ErrorMessages.ERROR_INVALID_SORT_INPUT_MESSAGE;

public class InvalidSortInputException extends Exception {
    @Override
    public String getMessage() {
        return ERROR_INVALID_SORT_INPUT_MESSAGE.toString();
    }
}

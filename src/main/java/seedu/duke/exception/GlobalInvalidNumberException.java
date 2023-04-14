package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class GlobalInvalidNumberException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_GLOBAL_INVALID_NUMBER.toString();
    }
}

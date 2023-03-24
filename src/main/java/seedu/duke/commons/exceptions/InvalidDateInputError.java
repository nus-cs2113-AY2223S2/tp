package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidDateInputError extends DukeError {
    public InvalidDateInputError () {
        super(ErrorMessages.ERROR_INVALID_DATE_INPUT.toString());
    }

}

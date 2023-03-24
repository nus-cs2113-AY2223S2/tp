package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidFilterInputError extends DukeError {
    public InvalidFilterInputError () {
        super(ErrorMessages.ERROR_INVALID_FILTER_INPUT.toString());
    }

}

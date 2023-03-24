package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class UnknownFilterInputError extends DukeError {
    public UnknownFilterInputError () {
        super(ErrorMessages.ERROR_FILTER_INPUT.toString());
    }

}

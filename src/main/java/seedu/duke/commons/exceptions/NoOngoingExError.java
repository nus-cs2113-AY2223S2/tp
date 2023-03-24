package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class NoOngoingExError extends DukeError {
    public NoOngoingExError () {
        super(ErrorMessages.ERROR_NO_ONGOING_EXERCISE.toString());
    }

}

package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class OngoingExProgressError extends DukeError {
    public OngoingExProgressError () {
        super(ErrorMessages.ERROR_ONGOING_EXERCISE_START_COMMAND.toString());
    }

}

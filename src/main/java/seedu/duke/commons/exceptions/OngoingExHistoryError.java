package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class OngoingExHistoryError extends DukeError {
    public OngoingExHistoryError () {
        super(ErrorMessages.ERROR_ONGOING_EXERCISE_HISTORY_COMMAND.toString());
    }

}

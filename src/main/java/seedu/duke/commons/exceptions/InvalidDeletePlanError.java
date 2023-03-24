package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidDeletePlanError extends DukeError {
    public InvalidDeletePlanError () {
        super(ErrorMessages.ERROR_INVALID_DELETE_COMMAND.toString());
    }

}

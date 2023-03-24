package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidPlanError extends DukeError {
    public InvalidPlanError () {
        super(ErrorMessages.ERROR_INVALID_PLAN.toString());
    }

}

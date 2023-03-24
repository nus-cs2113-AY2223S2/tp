package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidAddPlanError extends DukeError {
    public InvalidAddPlanError () {
        super(ErrorMessages.ERROR_INVALID_PLAN_ADDITION.toString());
    }

}

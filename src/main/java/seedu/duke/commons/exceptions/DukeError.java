package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class DukeError extends Exception {
    private ErrorMessages errorMessages;

    public DukeError (String message) {
        super(message);
    }

}

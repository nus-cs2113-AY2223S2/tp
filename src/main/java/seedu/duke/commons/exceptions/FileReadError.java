package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class FileReadError extends DukeError {
    public FileReadError () {
        super(ErrorMessages.ERROR_FILE_READ.toString());
    }

}

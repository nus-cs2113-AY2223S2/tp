package seedu.duke.commons.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidDifficultyInputError extends DukeError {
    public InvalidDifficultyInputError () {
        super(ErrorMessages.ERROR_DIFFICULTY_INPUT.toString());
    }

}

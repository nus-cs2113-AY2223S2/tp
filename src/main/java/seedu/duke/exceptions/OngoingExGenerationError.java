package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class OngoingExGenerationError extends DukeError{
    public OngoingExGenerationError(){
        super(ErrorMessages.ERROR_ONGOING_EXERCISE_GENERATE_COMMAND.toString());
    }
}

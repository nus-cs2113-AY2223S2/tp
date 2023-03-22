package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class OngoingExWriteError extends DukeError{
    public OngoingExWriteError(){
        super(ErrorMessages.ERROR_ONGOING_EXERCISE_TEST_SAMPLE.toString());
    }
}

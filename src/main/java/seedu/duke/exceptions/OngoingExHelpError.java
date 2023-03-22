package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class OngoingExHelpError extends DukeError{
    public OngoingExHelpError(){
        super(ErrorMessages.ERROR_ONGOING_EXERCISE_HELP_COMMAND.toString());
    }
}

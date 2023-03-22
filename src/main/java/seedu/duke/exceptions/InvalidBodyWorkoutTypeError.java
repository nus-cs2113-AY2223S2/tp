package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class InvalidBodyWorkoutTypeError extends DukeError{
    public InvalidBodyWorkoutTypeError(){
        super(ErrorMessages.ERROR_BODY_WORKOUT_TYPE_INPUT.toString());
    }
}

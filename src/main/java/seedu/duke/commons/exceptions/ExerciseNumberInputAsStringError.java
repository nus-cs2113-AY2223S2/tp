package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class ExerciseNumberInputAsStringError extends DukeError{
    public ExerciseNumberInputAsStringError(){
        super(ErrorMessages.ERROR_EXERCISE_NUM_INPUT_STRING.toString());
    }
}

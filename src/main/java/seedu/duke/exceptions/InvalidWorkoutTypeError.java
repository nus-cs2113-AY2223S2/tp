package seedu.duke.exceptions;

public class InvalidWorkoutTypeError extends DukeError{
    public InvalidWorkoutTypeError(){
        super("Incorrect workout type input");
    }
}

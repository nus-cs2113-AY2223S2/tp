package seedu.duke.exceptions;

public class OngoingExGenerationError extends DukeError{
    public OngoingExGenerationError(){
        super("Finish your exercise! Cannot generate new exercise");
    }
}

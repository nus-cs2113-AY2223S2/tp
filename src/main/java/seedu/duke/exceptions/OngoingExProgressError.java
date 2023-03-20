package seedu.duke.exceptions;

public class OngoingExProgressError extends DukeError{
    public OngoingExProgressError(){
        super("Exercise already in progress!");
    }
}

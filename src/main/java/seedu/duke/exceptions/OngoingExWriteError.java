package seedu.duke.exceptions;

public class OngoingExWriteError extends DukeError{
    public OngoingExWriteError(){
        super("Finish your exercise! Testing of our features can come after that :)");
    }
}

package seedu.duke.exceptions;

public class OngoingExHelpError extends DukeError{
    public OngoingExHelpError(){
        super("Finish your exercise! Cannot print help messages!");
    }
}

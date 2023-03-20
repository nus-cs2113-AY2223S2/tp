package seedu.duke.exceptions;

public class NoOngoingExError extends DukeError{
    public NoOngoingExError(){
        super("There is no current workout session!" +
                "Please start a session now");
    }
}

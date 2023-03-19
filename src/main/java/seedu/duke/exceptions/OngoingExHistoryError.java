package seedu.duke.exceptions;

public class OngoingExHistoryError extends DukeError{
    public OngoingExHistoryError(){
        super("Finish your exercise!" +
                "You can look and feel good about your previous workout sessions " +
                "later!");
    }
}

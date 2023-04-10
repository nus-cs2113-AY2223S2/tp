package seedu.exceptions;

public class NoFoodsException extends LifeTrackerException {

    public NoFoodsException() {
        super("No food was selected. Returning to main menu..");
    }
    
}

package seedu.Exceptions;

public class InvalidCommandException extends LifeTrackerException{

    public InvalidCommandException() {
        super("Please enter a valid command.");
    }
}

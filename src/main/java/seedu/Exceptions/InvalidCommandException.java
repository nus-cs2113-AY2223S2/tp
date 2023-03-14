package seedu.exceptions;

public class InvalidCommandException extends LifeTrackerException{

    public InvalidCommandException() {
        super("Please enter a valid command.");
    }
}

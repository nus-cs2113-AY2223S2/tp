package seedu.exceptions;

public class InvalidChoiceException extends LifeTrackerException{
    public InvalidChoiceException() {
        super("This is an invalid choice! Please input a valid choice!");
    }
}

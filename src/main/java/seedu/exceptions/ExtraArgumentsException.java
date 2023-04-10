package seedu.exceptions;

public class ExtraArgumentsException extends LifeTrackerException{
    public ExtraArgumentsException() {
        super("Oops! Too many arguments in your input!");
    }
}

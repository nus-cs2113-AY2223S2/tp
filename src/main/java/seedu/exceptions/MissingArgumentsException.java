package seedu.exceptions;

public class MissingArgumentsException extends LifeTrackerException {

    public MissingArgumentsException(String commandWord, String argument) {
        super("Oops! Missing argument " + argument + " for command " + commandWord);
    }
    
}

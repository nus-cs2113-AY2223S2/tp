package seedu.exceptions;

public class InvalidArgumentsException extends LifeTrackerException {
    public InvalidArgumentsException(String commandWord) {
        super("Error: Missing arguments for " + commandWord);
    }
}

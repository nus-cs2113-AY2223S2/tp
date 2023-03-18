package seedu.exceptions;

public class InvalidArgumentsException extends LifeTrackerException {
    public InvalidArgumentsException(String commandWord, String argument) {
        super("Error: Invalid arguments for " + argument + " for commnd " + commandWord);
    }
}

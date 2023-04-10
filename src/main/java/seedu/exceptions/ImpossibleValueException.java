package seedu.exceptions;

public class ImpossibleValueException extends LifeTrackerException{
    public ImpossibleValueException(String commandWord, String argument) {
        super(argument + " is an impossible value for " + commandWord + " please enter a valid one!");
    }
}

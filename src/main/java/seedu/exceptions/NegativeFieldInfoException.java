package seedu.exceptions;

public class NegativeFieldInfoException extends LifeTrackerException{
    public NegativeFieldInfoException(String commandWord, String argument) {
        super("Oops! You cannot enter a negative value for " + argument + " in command " + commandWord);
    }
}

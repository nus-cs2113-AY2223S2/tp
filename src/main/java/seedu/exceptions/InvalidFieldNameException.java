package seedu.exceptions;

public class InvalidFieldNameException extends LifeTrackerException{
    public InvalidFieldNameException(String commandWord, String argument) {
        super("Oops! Invalid field name " + argument + " for command " + commandWord);
    }
}

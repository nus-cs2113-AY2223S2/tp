package seedu.exceptions;

public class InvalidFieldInfoFormatException extends LifeTrackerException{
    public InvalidFieldInfoFormatException(String commandWord, String argument) {
        super(argument + " is not of valid format for " + commandWord + " please try again!");
    }
}

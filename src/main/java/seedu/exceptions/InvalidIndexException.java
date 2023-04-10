package seedu.exceptions;

public class InvalidIndexException extends LifeTrackerException {

    public InvalidIndexException(int index) {
        super(index + " is not a valid index!");
    }
    
}

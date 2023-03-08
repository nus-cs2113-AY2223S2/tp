package seedu.Exceptions;

public abstract class LifeTrackerException extends Exception{
    public LifeTrackerException(String errorMessage){
        super(errorMessage);
    }
}

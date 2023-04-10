package seedu.exceptions;

public class UnableToSaveDatabaseException extends LifeTrackerException {

    public UnableToSaveDatabaseException(String databaseName) {
        super("Unable to save " + databaseName);
    }
    
}

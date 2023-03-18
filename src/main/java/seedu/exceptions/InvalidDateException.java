package seedu.exceptions;

import seedu.constants.DateConstants;

public class InvalidDateException extends LifeTrackerException {

    public InvalidDateException(String dateString) {
        super("Oops! " + dateString + " is not a valid date!" + 
                " Please format the date as: " + DateConstants.PARSE_FORMAT);
    }
    
}

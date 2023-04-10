package pocketpal.frontend.exceptions;

import pocketpal.frontend.constants.MessageConstants;

/**
 * Exception thrown when user input entry ID is not recognised.
 */
public class InvalidEntryIdException extends Exception {
    public InvalidEntryIdException(String message) {
        super(message);
    }

    public InvalidEntryIdException() {
        super(MessageConstants.MESSAGE_INVALID_ID);
    }
}

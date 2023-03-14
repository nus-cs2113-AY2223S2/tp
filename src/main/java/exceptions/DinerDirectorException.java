package exceptions;

/**
 * Handles Exceptions related to the Diner Director CLI Application.
 */

public class DinerDirectorException extends Exception {
    /**
     * Constructor that initiates the exception message.
     *
     * @param message The exception message to print.
     */
    public DinerDirectorException(String message) {
        super(message);
    }
}

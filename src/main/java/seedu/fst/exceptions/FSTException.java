package seedu.fst.exceptions;

/**
 * Represents a FSTException Class
 */
public class FSTException extends Exception {

    protected String errorMessage = "Error..";


    public FSTException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public FSTException() {
    }

    public String getMessage() {
        return errorMessage;
    }

}

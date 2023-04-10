package seedu.moneymind.exceptions;

/**
 * Throws exception when the user input an invalid command.
 */
public class InvalidCommandException extends Exception {
    private String errorMessage;

    public InvalidCommandException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void showErrorMessage() {
        System.out.println(errorMessage);
    }

}

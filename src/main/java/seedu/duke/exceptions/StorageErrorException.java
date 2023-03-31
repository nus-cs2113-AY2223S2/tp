package seedu.duke.exceptions;

public class StorageErrorException extends Exception {
    public void loadingError() {
        System.out.println("There was an error in loading.");
    }
}

package seedu.fst.exceptions;

public class ReadStorageFileErrorException extends FSTException {
    public ReadStorageFileErrorException(String message) {
        super("The attribute '" + message + "' in the storage file is not valid.");
    }
}

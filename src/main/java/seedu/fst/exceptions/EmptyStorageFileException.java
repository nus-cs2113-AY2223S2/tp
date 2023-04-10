package seedu.fst.exceptions;


public class EmptyStorageFileException extends FSTException {
    public EmptyStorageFileException() {
        super("Line in storage file is empty. Data may be corrupted.");
    }
}

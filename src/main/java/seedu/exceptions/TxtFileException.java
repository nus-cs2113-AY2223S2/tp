package seedu.exceptions;

public class TxtFileException extends Exception {
    @Override
    public String getMessage() {
        return "TxtFile has been corrupted, the corrupted entry has been deleted";
    }
}

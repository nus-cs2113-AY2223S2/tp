package seedu.fst.exceptions;

public class InvalidFlagException extends FSTException {
    public InvalidFlagException(String flag) {
        this.errorMessage = "the flag " + "\"--" + flag + "\"" + " is invalid";
    }
}

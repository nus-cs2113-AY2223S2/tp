package seedu.fst.exceptions;

public class InvalidFlagValueException extends FSTException {

    public InvalidFlagValueException(String flag) {
        this.errorMessage = "the value of flag " + "\"--" + flag + "\"" + " is invalid";
    }
}

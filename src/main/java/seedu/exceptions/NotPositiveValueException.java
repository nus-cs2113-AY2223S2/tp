package seedu.exceptions;

public class NotPositiveValueException extends Exception {
    @Override
    public String getMessage() {
        return "Budget set is of a negative value!";
    }
}

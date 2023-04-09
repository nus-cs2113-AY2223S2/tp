package seedu.exceptions;

public class LargeValueException extends Exception {
    @Override
    public String getMessage() {
        return "The amount you have entered is too large please input a smaller value!";
    }
}

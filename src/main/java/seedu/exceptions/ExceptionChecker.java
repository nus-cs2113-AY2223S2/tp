package seedu.exceptions;

public class ExceptionChecker {
    public static void checkEmptyString(String string) throws EmptyStringException {
        if (string.isEmpty()) {
            throw new EmptyStringException();
        }
    }
}


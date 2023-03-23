package exception;

public class SniffException extends Exception {
    protected static String errorMessage;

    public static String getErrorMessage() {
        return errorMessage;
    }

    public SniffException(String e) {
        errorMessage = e;
    }
    public static void showError() {
        System.out.println(errorMessage);
    }
}

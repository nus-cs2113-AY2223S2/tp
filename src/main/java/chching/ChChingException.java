package chching;

/**
 * Models a class for exception thrown
 */
public class ChChingException extends Exception {
    private String message;
    public ChChingException(String message) {
        super(message);
        this.message = message;
    }

    public void printChChingException() {
        System.out.println(message);
    }
}

